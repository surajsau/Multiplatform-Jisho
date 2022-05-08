package `in`.surajsau.jisho.android.ui.components

import `in`.surajsau.jisho.android.neomorphic.NeomorphicShape
import `in`.surajsau.jisho.android.neomorphic.neomorph
import `in`.surajsau.jisho.android.ui.theme.AppTheme
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    title: String,
    navigateUpIcon: ImageVector,
    menu: @Composable (BoxScope.() -> Unit)? = null,
    onNavigateUp: () -> Unit = {}
) {
   AppToolbar(
       modifier = modifier,
       title = {
           Text(
               text = title,
               style = MaterialTheme.typography.h5,
               fontWeight = FontWeight.Medium
           )
       },
       navigateUpIcon = {
           Icon(imageVector = navigateUpIcon, contentDescription = "")
       },
       menu = { menu?.invoke(this) },
       onNavigateUp = onNavigateUp,
   )
}

@Composable
@Stable
fun AppToolbar(
    modifier: Modifier = Modifier,
    title: @Composable BoxScope.() -> Unit = {},
    menu: @Composable (BoxScope.() -> Unit)? = null,
    navigateUpIcon: @Composable (BoxScope.() -> Unit)? = null,
    onNavigateUp: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .padding(all = 16.dp),
    ) {
        navigateUpIcon?.let {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { onNavigateUp() }
                    .neomorph(
                        shape = NeomorphicShape.Oval,
                        elevation = 8.dp,
                        animatePress = true,
                    )
                    .padding(all = 12.dp),
                contentAlignment = Alignment.Center
            ) { it.invoke(this) }
        }

        Box(
            modifier = Modifier.align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            title()
        }

        menu?.let {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .neomorph(),
                contentAlignment = Alignment.Center
            ) { it.invoke(this) }
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun PreviewAppToolbar() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            AppToolbar(
                modifier = Modifier.fillMaxWidth(),
                title = "Sample Title",
                navigateUpIcon = Icons.Default.ArrowBack,
            ) {}
        }
    }
}