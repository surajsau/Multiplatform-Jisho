package `in`.surajsau.jisho.model

import `in`.surajsau.jisho.data.model.Conjugation

/**
 * Conjugation.conj mapping
 * id	name
  1	Non-past
  2	Past (~ta)
  3	Conjunctive (~te)
  4	Provisional (~eba)
  5	Potential
  6	Passive
  7	Causative
  8	Causative-Passive
  9	Volitional
  10	Imperative
  11	Conditional (~tara)
  12	Alternative (~tari)
  13	Continuative (~i)
 */

sealed class ConjugationResult(
  private val word: String,
  private val conjugations: List<Conjugation>,
) {

  val root: String
    get() = word.substring(startIndex = 0, endIndex = word.length - 1)

  val presentFormal: String
    get() = conjugations
      .firstOrNull { it.isFormal && it.conj == 1L }
      ?.let { root + it.okurigana } ?: ""

  val presentFormalNegative: String
    get() = conjugations
      .firstOrNull { it.isFormal && it.conj == 1L && it.isNegative }
      ?.let { root + it.okurigana } ?: ""

  val pastFormal: String
    get() = conjugations
      .firstOrNull { it.isFormal && it.conj == 2L }
      ?.let { root + it.okurigana } ?: ""

  val pastFormalNegative: String
    get() = conjugations
      .firstOrNull { it.isFormal && it.conj == 2L && it.isNegative }
      ?.let { root + it.okurigana } ?: ""

  val presentInformal: String
    get() = conjugations
      .firstOrNull { !it.isFormal && it.conj == 1L }
      ?.let { root + it.okurigana } ?: ""

  val presentInformalNegative: String
    get() = conjugations
      .firstOrNull { !it.isFormal && it.conj == 1L && it.isNegative }
      ?.let { root + it.okurigana } ?: ""

  val pastInformal: String
    get() = conjugations
      .firstOrNull { !it.isFormal && it.conj == 2L }
      ?.let { root + it.okurigana } ?: ""

  val pastInformalNegative: String
    get() = conjugations
      .firstOrNull { it.isFormal && it.conj == 2L && it.isNegative }
      ?.let { root + it.okurigana } ?: ""

  val te: String
    get() = conjugations
      .firstOrNull { it.conj == 3L }
      ?.let { root + it.okurigana } ?: ""

  data class Verb(
    private val word: String,
    private val conjugations: List<Conjugation>,
  ): ConjugationResult(word, conjugations) {

    val imperative: String
      get() = conjugations
        .firstOrNull { it.conj == 10L }
        ?.let { root + it.okurigana } ?: ""

    val volitional: String
      get() = conjugations
        .firstOrNull { it.conj == 9L }
        ?.let { root + it.okurigana } ?: ""

    val causative: String
      get() = conjugations
        .firstOrNull { it.conj == 7L }
        ?.let { root + it.okurigana } ?: ""

    val hypothetical: String
      get() = conjugations
        .firstOrNull { it.conj == 4L }
        ?.let { root + it.okurigana } ?: ""

    val potential: String
      get() = conjugations
        .firstOrNull { it.conj == 5L }
        ?.let { root + it.okurigana } ?: ""
  }

  data class Adjective(
    private val word: String,
    private val conjugations: List<Conjugation>
  ): ConjugationResult(word, conjugations)
}