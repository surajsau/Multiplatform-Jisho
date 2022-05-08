package `in`.surajsau.jisho.android

import `in`.surajsau.jisho.android.ui.JishoApp
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { JishoApp() }
    }
}
