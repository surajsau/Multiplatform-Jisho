package `in`.surajsau.jisho.search.components

import android.content.res.Configuration
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.surajsau.jisho.ui.R
import `in`.surajsau.jisho.ui.theme.JishoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    collapsed: Boolean,
    text: String,
    onTextChanged: (String) -> Unit
) {
    val transition = updateTransition(
        targetState = collapsed,
        label = "Search Bar"
    )

    val searchBarHeight by transition.animateDp(label = "Height") {
        if (collapsed) 56.dp else 152.dp
    }

    val searchBarFontSize by transition.animateInt(label = "Text Size") {
        if (collapsed) 18 else 36
    }

    val padding by transition.animateDp(label = "Padding") {
        if (collapsed) 16.dp else 0.dp
    }

    val cornerRadius by transition.animateDp(label = "Padding") {
        if (collapsed) 36.dp else 0.dp
    }

    Box(
        modifier = modifier
            .padding(padding)
            .height(searchBarHeight)
            .background(
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
                shape = RoundedCornerShape(cornerRadius)
            ),
        contentAlignment = if (collapsed) Alignment.CenterStart else Alignment.BottomStart
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            placeholder = {
                Text(
                    text = "Search",
                    style = TextStyle(fontSize = searchBarFontSize.sp)
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
            textStyle = TextStyle(
                fontSize = searchBarFontSize.sp
            )
        )
    }
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
private fun previewSearchBar() {
    JishoTheme {
        SearchBar(
            text = "",
            collapsed = false,
            onTextChanged = {}
        )
    }
}
