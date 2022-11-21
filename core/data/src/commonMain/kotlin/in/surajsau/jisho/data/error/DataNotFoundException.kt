package `in`.surajsau.jisho.data.error

public class DataNotFoundException(query: String) : Exception("Data not found for query: $query")
