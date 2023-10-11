import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import feature.app.App
import feature.app.AppViewModel

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow {
        App(AppViewModel(), Modifier.fillMaxSize())
    }
}