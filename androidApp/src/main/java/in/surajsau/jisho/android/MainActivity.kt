package `in`.surajsau.jisho.android

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import `in`.surajsau.jisho.android.ui.JishoApp
import `in`.surajsau.jisho.app.AppViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModel()

    private var hideSplash: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    hideSplash = it.hideSplash
                }
            }
        }

        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()

            if (state.startDestination != null) {
                JishoApp(
                    startDestination = state.startDestination!!
                )
            }
        }

        val content = findViewById<View>(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return when {
                        hideSplash -> {
                            content.viewTreeObserver.removeOnPreDrawListener(this)
                            true
                        }
                        else -> false
                    }
                }
            }
        )
    }
}
