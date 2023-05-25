package `in`.surajsau.jisho.model

public enum class KanjiGrade(
    public val grade: Int,
    public val title: String
) {
    Grade1(0, "1年"),
    Grade2(1, "2年"),
    Grade3(2, "3年"),
    Grade4(3, "4年"),
    Grade5(4, "5年"),
    Grade6(5, "6年"),

    Grade7(6, "1年"),
    Grade8(7, "2年"),
    Grade9(8, "3年"),

    AllSchool(9, "All schools"),
}