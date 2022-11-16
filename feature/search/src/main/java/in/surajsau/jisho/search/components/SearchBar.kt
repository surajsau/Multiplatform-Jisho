package `in`.surajsau.jisho.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.surajsau.jisho.android.neomorphic.NeomorphicShape
import `in`.surajsau.jisho.android.neomorphic.neomorph
import `in`.surajsau.jisho.android.ui.theme.DarkColors

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
) {
    TextField(
        modifier = modifier
            .neomorph(
                isPressed = true,
                shape = NeomorphicShape.RoundedCorner(16.dp),
                elevation = 6.dp,
            ),
        value = text,
        placeholder = { Text(text = "Search") },
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "") },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.onSecondary
        ),
        shape = MaterialTheme.shapes.small.copy(all = CornerSize(8.dp)),
        onValueChange = onTextChanged
    )
}

@Preview
@Composable
private fun previewSearchBar() {
    MaterialTheme(colors = DarkColors) {
        Surface {
            Box(modifier = Modifier.fillMaxSize()) {
                SearchBar(
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.TopCenter),
                    text = "",
                    onTextChanged = {}
                )
            }
        }
    }
}
