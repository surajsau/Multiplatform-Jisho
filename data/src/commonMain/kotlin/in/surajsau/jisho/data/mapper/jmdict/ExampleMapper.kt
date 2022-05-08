package `in`.surajsau.jisho.data.mapper.jmdict

import `in`.surajsau.jisho.data.model.jmdict.Example

fun String.exampleFromDb(sentenceString: String): List<Example> {
    val sentences = sentenceString.split(";")
    val texts = split(";")

    return texts.mapIndexed { index, text ->
        Example(
            text,
            sentences = sentences[index].split("/")
        )
    }
}