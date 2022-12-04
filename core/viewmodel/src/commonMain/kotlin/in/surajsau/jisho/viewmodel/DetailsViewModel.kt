package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.data.GetConjugations
import `in`.surajsau.jisho.data.GetEntry
import `in`.surajsau.jisho.data.GetKanji
import `in`.surajsau.jisho.data.GetNumberOfSentencesForWord
import `in`.surajsau.jisho.data.GetSentencesForWord
import `in`.surajsau.jisho.data.GetBucket
import `in`.surajsau.jisho.model.Bucket
import `in`.surajsau.jisho.model.ConjugationResult
import `in`.surajsau.jisho.model.SentenceQuery
import `in`.surajsau.jisho.model.SentenceResult
import `in`.surajsau.jisho.model.jmdict.Entry
import `in`.surajsau.jisho.model.kanjidic.Literal
import `in`.surajsau.jisho.utils.isKanji
import `in`.surajsau.jisho.viewmodel.expected.BaseViewModel
import `in`.surajsau.jisho.viewmodel.expected.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

public class DetailsViewModel : BaseViewModel<DetailsState>(), KoinComponent {

    private val getEntry: GetEntry = get()
    private val getKanji: GetKanji = get()
    private val geBucket: GetBucket = get()
    private val getSentencesForWord: GetSentencesForWord = get()
    private val getNumberOfSentencesForWord: GetNumberOfSentencesForWord = get()
    private val getConjugations: GetConjugations = get()

    private val _entry = MutableStateFlow<Entry?>(null)

    private val _sentenceResults = MutableStateFlow<List<SentenceResult>>(emptyList())
    private val _totalSentences = MutableStateFlow<Int>(0)

    private val _conjugation = MutableStateFlow<Conjugation?>(null)
    private val _kanjis = MutableStateFlow<List<KanjiItem>>(emptyList())

    private val _bucket = MutableStateFlow<Bucket?>(null)

    private val _sentences = combine(
        _sentenceResults,
        _totalSentences
    ) { sentences, totalSentences ->
        Sentences(
            items = sentences,
            sentenceCount = totalSentences ?: 0
        )
    }

    override val state: StateFlow<DetailsState> = combine(
            _entry.filterNotNull(),
            _conjugation,
            _kanjis,
            _sentences,
            _bucket
        ) { values ->
            val entry = values[0] as Entry
            val conjugation = values[1] as? Conjugation
            val kanjis = values[2] as List<KanjiItem>
            val sentences = values[3] as Sentences
            val bucket = values[4] as? Bucket

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

            DetailsState(
                header = header,
                alternatives = alternatives,
                meanings = meanings,
                kanjis = kanjis,
                sentences = sentences,
                conjugations = conjugation,
                bucket = bucket
            )
        }
        .stateIn(scope, SharingStarted.WhileSubscribed(), DetailsState())

    public fun initWith(id: Long, word: String) {
        scope.launch {
            val entry = getEntry(id)
            _entry.value = entry

            _conjugation.value = getConjugations(
                word = when {
                    entry.kanjis.isNotEmpty() -> entry.kanjis.first().value
                    entry.readings.isNotEmpty() -> entry.readings.first().value
                    else -> ""
                },
                pos = entry.senses[0].particleOfSpeeches[0]
            )?.let(this@DetailsViewModel::map)

            _sentenceResults.value = getSentencesForWord(
                query = SentenceQuery(word = word),
                count = MaximumSentencesToBeShown
            )

            _totalSentences.value = getNumberOfSentencesForWord(query = SentenceQuery(word = word))

            _kanjis.value = word.filter { it.isKanji }
                .map { getKanji(literal = it.toString()) }
                .map(this@DetailsViewModel::map)
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

    public fun onEditTagClicked() {

    }

    public data class Sentences(
        val items: List<SentenceResult>,
        private val sentenceCount: Int,
    ) {
        val showMore: Boolean
            get() = sentenceCount > MaximumSentencesToBeShown

        val showMoreText: String
            get() = "Show ${sentenceCount - MaximumSentencesToBeShown} more sentences"
    }

    public data class Conjugation(
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

    public data class Header(
        val kanji: String,
        val onyomi: String,
    )

    public data class Meaning(
        val pos: String,
        val value: String,
    )

    public data class KanjiItem(
        val value: String,
        val meaning: String,
    )

    public companion object {
        private const val MaximumSentencesToBeShown = 5
    }
}

public data class DetailsState(
    val header: DetailsViewModel.Header = DetailsViewModel.Header("", ""),
    val alternatives: String = "",
    val bucket: Bucket? = null,
    val meanings: List<DetailsViewModel.Meaning> = emptyList(),
    val kanjis: List<DetailsViewModel.KanjiItem> = emptyList(),
    val sentences: DetailsViewModel.Sentences = DetailsViewModel.Sentences(emptyList(), 0),
    val conjugations: DetailsViewModel.Conjugation? = null,
): State {

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
}
