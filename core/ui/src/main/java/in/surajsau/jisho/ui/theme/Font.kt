package `in`.surajsau.jisho.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Typography

val Typography.sectionTitle: TextStyle
    get() = this.headlineSmall.copy(fontWeight = FontWeight.Medium)

val Typography.cardTitle: TextStyle
    get() = this.titleMedium.copy(fontWeight = FontWeight.SemiBold)

val Typography.cardDescription: TextStyle
    get() = this.bodyMedium