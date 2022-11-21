package `in`.surajsau.jisho.model.mapper.jmdict

import `in`.surajsau.jisho.model.jmdict.Info
import `in`.surajsau.jisho.model.jmdict.Sense

public fun String.senseFromDb(
    particleString: String,
    fieldString: String,
    antonymString: String,
    dialectString: String,
    exampleString: String,
    exampleSentenceString: String
): List<Sense> {
    val particles = particleString.split(";")
    val fields = fieldString.split(";")
    val antonyms = antonymString.split(";")
    val dialects = dialectString.split(";")
    val exampleTexts = exampleString.split(";")
    val exampleSentences = exampleSentenceString.split(";")

    return split(";").mapIndexed { index, glossString ->
        Sense(
            glosses = glossString.glossFromDb(),
            particleOfSpeeches = particles[index].split("|").map { Info(it) },
            antonyms = antonyms[index].antonymFromDb(),
            dialects = dialects[index].dialectFromDb(),
            fields = fields[index].split("|").map { Info(it) },
            example = exampleTexts[index].exampleFromDb(sentenceString = exampleSentences[index])
        )
    }
}