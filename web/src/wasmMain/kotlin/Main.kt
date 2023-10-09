import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import feature.home.HomeScreen
import feature.home.HomeViewModel

@OptIn(ExperimentalComposeUiApi::class)
fun main() {

    val viewModel = HomeViewModel()

    CanvasBasedWindow("Test") {
        HomeScreen(viewModel)
    }
}