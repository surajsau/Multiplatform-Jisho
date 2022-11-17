package `in`.surajsau.jisho.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import `in`.surajsau.jisho.android.ui.JishoApp
import `in`.surajsau.jisho.app.AppViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<AppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JishoApp()
        }
    }
}
