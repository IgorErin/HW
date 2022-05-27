package compose

import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import compose.view.View

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "tic-tac-toe",
        state = rememberWindowState(width = 600.dp, height = 450.dp)
    ) {
        val viewModel = remember { ViewModel() }
        View(viewModel)
    }
}
