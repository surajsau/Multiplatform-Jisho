package `in`.surajsau.jisho.android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

val Typography.sectionTitle: TextStyle
    get() = this.h6.copy(fontWeight = FontWeight.Medium)

val Typography.cardTitle: TextStyle
    get() = this.subtitle1.copy(fontWeight = FontWeight.SemiBold)

val Typography.cardDescription: TextStyle
    get() = this.subtitle2