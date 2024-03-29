package `in`.surajsau.jisho.search.components

import android.content.res.Configuration
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.surajsau.jisho.ui.R
import `in`.surajsau.jisho.ui.theme.PreviewContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    focused: Boolean,
    text: String,
    onTextChanged: (String) -> Unit,
    onKeyboardAction: () -> Unit,
) {
    val transition = updateTransition(targetState = focused, label = "Search Bar")
    val boxElevation by transition.animateDp(label = "Padding") { if (it) 2.dp else 0.dp }
    val textFieldPadding by transition.animateDp(label = "Text field padding") { if(it) 0.dp else 16.dp }

    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceColorAtElevation(boxElevation))
            .padding(all = textFieldPadding),
        contentAlignment = Alignment.CenterStart
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                    shape = RoundedCornerShape(36.dp)
                ),
            value = text,
            placeholder = {
                Text(
                    text = "Search",
                    style = TextStyle(fontSize = 16.sp),
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search"
                )
            },
            onValueChange = onTextChanged,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = 16.sp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                capitalization = KeyboardCapitalization.None
            ),
            keyboardActions = KeyboardActions { onKeyboardAction() }
        )
    }
}

private class SearchBarStateParam: PreviewParameterProvider<SearchBarStateParam.Data> {

    override val values: Sequence<Data>
        get() = sequenceOf(
            Data(collapsed = true, text = ""),
            Data(collapsed = true, text = "嬉しい"),
            Data(collapsed = false, text = ""),
            Data(collapsed = false, text = "嬉しい"),
        )
    data class Data(
        val collapsed: Boolean,
        val text: String
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun previewSearchBar(@PreviewParameter(SearchBarStateParam::class) state: SearchBarStateParam.Data) {
    PreviewContainer {
        Surface {
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                text = state.text,
                focused = state.collapsed,
                onTextChanged = {},
                onKeyboardAction = {}
            )
        }
    }
}
