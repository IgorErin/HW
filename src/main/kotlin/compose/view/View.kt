package compose.view

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import compose.Screen
import compose.ViewModel
import compose.view.screen.GameScreen
import compose.view.screen.StartScreen

@Composable
fun View(viewModel: ViewModel) {
    val state = viewModel.state

    MaterialTheme {
        when(state.screen) {
            Screen.StartScreen -> {
                StartScreen(
                    viewModel.sides,
                    viewModel.games,
                    viewModel::onGameSelect,
                    viewModel::onSideSelect
                )
            }
            Screen.GameScreen -> {
                GameScreen(
                    state.isWin, //TODO()
                    state.isWin,
                    state.fields,
                    viewModel::onFieldSelect,
                    viewModel::onBackClick
                )
            }
        }
    }
}
