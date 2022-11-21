package `in`.surajsau.jisho.model.mapper.jmdict

import `in`.surajsau.jisho.model.jmdict.Example

public fun String.exampleFromDb(sentenceString: String): List<Example> {
    val sentences = sentenceString.split(";")
    val texts = split(";")

    return texts.mapIndexed { index, text ->
        Example(
            text,
            sentences = sentences[index].split("/")
        )
    }
}