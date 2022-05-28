package compose.view

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import compose.GameVariant
import compose.Screen
import compose.ViewModel
import compose.view.screen.GameScreen
import compose.view.screen.StartScreen

@Composable
fun View(viewModel: ViewModel) {
    val state = viewModel.state
    val isPlayerWin = state.nextFieldState != state.side

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
                    isPlayerWin,
                    state.isWin,
                    state.fields,
                    viewModel::onFieldSelect,
                    viewModel::onBackClick,
                    isBotMove(state),
                    state.botLineIndex,
                    state.botColumnIndex
                )
            }
        }
    }
}

fun isBotMove(state: ViewModel.State) = state.gameVariant != GameVariant.Single && state.nextFieldState != state.side
