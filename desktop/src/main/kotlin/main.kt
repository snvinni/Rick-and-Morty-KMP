
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import feature.app.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App(Modifier.fillMaxSize())
    }
}

@Preview
@Composable
fun DefaultPreview() {
    App(Modifier.fillMaxSize())
}