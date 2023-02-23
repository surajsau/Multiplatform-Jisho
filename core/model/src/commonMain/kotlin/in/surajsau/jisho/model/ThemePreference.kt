package `in`.surajsau.jisho.model

public sealed interface ThemePreference {
    public data class Dynamic(val dark: Boolean): ThemePreference
    public data class Default(val dark: Boolean): ThemePreference
    public object SystemDefault: ThemePreference
    public object DynamicSystemDefault: ThemePreference
}