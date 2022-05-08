package `in`.surajsau.jisho.data.error

class DataNotFoundException(query: String): Exception("Data not found for query: $query")