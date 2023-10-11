import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import feature.home.HomeScreen
import feature.home.HomeViewModel

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        HomeScreen(HomeViewModel())
    }
}

@Preview
@Composable
fun DefaultPreview() {
    HomeScreen(HomeViewModel())
}