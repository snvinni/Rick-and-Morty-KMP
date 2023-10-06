import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import ui.App
import ui.TestViewModel

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    val viewModel = TestViewModel()

    CanvasBasedWindow("Test") {
        App(viewModel)
    }
}