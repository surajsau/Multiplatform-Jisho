package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.data.model.kanjidic.Literal
import `in`.surajsau.jisho.expected.BaseViewModel
import `in`.surajsau.jisho.model.BucketQuery
import `in`.surajsau.jisho.model.BucketResult
import `in`.surajsau.jisho.model.ConjugationResult
import `in`.surajsau.jisho.model.EntryResult
import `in`.surajsau.jisho.model.SentenceQuery
import `in`.surajsau.jisho.model.SentenceResult
import `in`.surajsau.jisho.usecase.AddToBucket
import `in`.surajsau.jisho.usecase.GetConjugations
import `in`.surajsau.jisho.usecase.GetEntry
import `in`.surajsau.jisho.usecase.GetKanji
import `in`.surajsau.jisho.usecase.GetNumberOfSentencesForWord
import `in`.surajsau.jisho.usecase.GetSentencesForWord
import `in`.surajsau.jisho.usecase.RemoveFromBucket
import `in`.surajsau.jisho.utils.Optional
import `in`.surajsau.jisho.utils.isKanji
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class DetailsViewModel : BaseViewModel<DetailsViewModel.State, DetailsViewModel.Intent, DetailsViewModel.Effect>(), KoinComponent {

    private val getEntry: GetEntry = get()
    private val getKanji: GetKanji = get()
    private val getSentencesForWord: GetSentencesForWord = get()
    private val getNumberOfSentencesForWord: GetNumberOfSentencesForWord = get()
    private val getConjugations: GetConjugations = get()
    private val addToBucket: AddToBucket = get()
    private val removeFromBucket: RemoveFromBucket = get()

    private val _entry = MutableStateFlow<Optional<EntryResult>>(Optional.Empty)

    private val _sentences = MutableStateFlow<List<SentenceResult>>(emptyList())
    private val _totalSentences = MutableStateFlow<Optional<Int>>(Optional.Empty)

    private val _conjugation = MutableStateFlow<Optional<Conjugation>>(Optional.Empty)
    private val _kanjis = MutableStateFlow<List<KanjiItem>>(emptyList())

    private val _effect = Channel<Effect>(Channel.UNLIMITED)

    override val state: StateFlow<State>
        get() = combine(
            _entry.filterNot { it.isEmpty }.map { it.value!! },
            _conjugation.map { it.value },
            _kanjis,
            combine(
                _sentences,
                _totalSentences
            ) { sentences, totalSentences ->
                Sentences(
                    items = sentences,
                    sentenceCount = totalSentences.value ?: 0
                )
            }
        ) { entry, conjugation, kanjis, sentences ->

            val meanings = entry.senses.map { sense ->
                val value = sense.glosses.joinToString(", ") { it.value }
                val pos = sense.particleOfSpeeches.let { infos ->

                    val (verbs, nonVerbs) = infos.partition { it.value.startsWith("v") }
                    return@let (nonVerbs + verbs).joinToString(", ") { it.description }
                }

                Meaning(pos, value)
            }

            val header = Header(
                kanji = entry.kanjis.firstOrNull()?.value ?: "",
                onyomi = entry.readings.firstOrNull()?.value ?: ""
            )

            val alternatives = entry.kanjis.drop(1).joinToString { it.value }

            State(
                header = header,
                alternatives = alternatives,
                meanings = meanings,
                kanjis = kanjis,
                sentences = sentences,
                conjugations = conjugation,
                bucket = entry.bucket
            )
        }
            .stateIn(scope, SharingStarted.WhileSubscribed(), State.Init)

    override val effect: Flow<Effect>
        get() = _effect.receiveAsFlow()

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.InitWith -> {
                scope.launch {
                    val entry = getEntry(intent.id)
                    _entry.emit(Optional.of(entry))

                    val sentences = getSentencesForWord(
                        query = SentenceQuery(word = intent.word),
                        count = MaximumSentencesToBeShown
                    )
                    _sentences.emit(sentences)

                    val totalSentences = getNumberOfSentencesForWord(query = SentenceQuery(word = intent.word))
                    _totalSentences.emit(Optional.of(totalSentences))

                    val kanjis = intent.word.filter { it.isKanji }
                        .map { getKanji(literal = it.toString()) }
                        .map(this@DetailsViewModel::map)
                    _kanjis.emit(kanjis)

                    val conjugations = getConjugations(
                        word = when {
                            entry.kanjis.isNotEmpty() -> entry.kanjis.first().value
                            entry.readings.isNotEmpty() -> entry.readings.first().value
                            else -> ""
                        },
                        pos = entry.senses[0].particleOfSpeeches[0]
                    )?.let(this@DetailsViewModel::map)
                    _conjugation.emit(Optional.of(conjugations))
                }
            }

            is Intent.AddToBookmark -> {
                val id = _entry.value.value?.id ?: return

                scope.launch {
                    addToBucket(query = BucketQuery.Entry(id = id, bucketId = intent.bookmarkId))
                }
            }

            is Intent.RemoveFromBookmark -> {
                val id = _entry.value.value?.id ?: return

                scope.launch {
                    // bucket id isn't necessary for removing
                    removeFromBucket(query = BucketQuery.Entry(id = id, bucketId = -1))
                }
            }
        }
    }

    private fun map(literal: Literal): KanjiItem {
        val value = literal.value
        val meaning = literal
            .meanings
            .filter { it.type == "None" }
            .joinToString(", ") { it.value }

        return KanjiItem(value, meaning)
    }

    private fun map(result: ConjugationResult): Conjugation {
        return Conjugation(
            presentFormal = result.presentFormal,
            presentFormalNegative = result.presentFormalNegative,
            pastFormal = result.pastFormal,
            pastFormalNegative = result.pastFormalNegative,
            presentInformal = result.presentInformal,
            presentInformalNegative = result.presentInformalNegative,
            pastInformal = result.pastInformal,
            pastInformalNegative = result.pastInformalNegative,
            te = result.te,
        ).apply {
            if (result is ConjugationResult.Verb) {
                this.causative = result.causative
                this.hypothetical = result.hypothetical
                this.volitional = result.volitional
                this.imperative = result.imperative
                this.potential = result.potential
            }
        }
    }

    data class State(
        val header: Header,
        val alternatives: String,
        val meanings: List<Meaning>,
        val kanjis: List<KanjiItem>,
        val sentences: Sentences,
        val conjugations: Conjugation?,
        val bucket: BucketResult?,
    ) : VMState {

        val showAlternative: Boolean
            get() = this.alternatives.isNotEmpty()

        val showKanji: Boolean
            get() = this.kanjis.isNotEmpty()

        val showMeanings: Boolean
            get() = this.meanings.isNotEmpty()

        val showSentences: Boolean
            get() = this.sentences.items.isNotEmpty()

        val showConjugation: Boolean
            get() = conjugations != null

        companion object {
            val Init = State(
                header = Header("", ""),
                alternatives = "",
                meanings = emptyList(),
                kanjis = emptyList(),
                sentences = Sentences(emptyList(), 0),
                conjugations = null,
                bucket = null
            )
        }
    }

    data class Sentences(
        val items: List<SentenceResult>,
        private val sentenceCount: Int,
    ) {
        val showMore: Boolean
            get() = sentenceCount > MaximumSentencesToBeShown

        val showMoreText: String
            get() = "Show ${sentenceCount - MaximumSentencesToBeShown} more sentences"
    }

    data class Conjugation(
        val presentFormal: String,
        val presentFormalNegative: String,
        val pastFormal: String,
        val pastFormalNegative: String,
        val presentInformal: String,
        val presentInformalNegative: String,
        val pastInformal: String,
        val pastInformalNegative: String,
        val te: String,
    ) {

        var imperative: String = ""
        val showImperative: Boolean
            get() = imperative.isNotEmpty()

        var volitional: String = ""
        val showVolitional: Boolean
            get() = imperative.isNotEmpty()

        var causative: String = ""
        val showCausative: Boolean
            get() = imperative.isNotEmpty()

        var hypothetical: String = ""
        val showHypothetical: Boolean
            get() = imperative.isNotEmpty()

        var potential: String = ""
        val showPotential: Boolean
            get() = imperative.isNotEmpty()
    }

    sealed interface Intent : VMIntent {
        data class InitWith(val id: Long, val word: String) : Intent

        data class AddToBookmark(val bookmarkId: Long): Intent
        object RemoveFromBookmark: Intent
    }

    sealed interface Effect : VMEffect

    data class Header(
        val kanji: String,
        val onyomi: String,
    )

    data class Meaning(
        val pos: String,
        val value: String,
    )

    data class KanjiItem(
        val value: String,
        val meaning: String,
    )

    companion object {
        private const val MaximumSentencesToBeShown = 5
    }
}
