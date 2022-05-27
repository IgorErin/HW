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
    val games = viewModel.games
    val sides = viewModel.sides

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
                    state.gameVariant,
                    state.fields,
                    viewModel::onFieldSelect
                )
            }
            Screen.WinScreen -> {

            }
        }
    }
}