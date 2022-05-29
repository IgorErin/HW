package compose.view

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import compose.Screen
import compose.ViewModel
import compose.view.screen.GameScreen
import compose.view.screen.StartScreen

@Suppress("FunctionNaming")
@Composable
fun View(viewModel: ViewModel) {
    val state = viewModel.state
    val isPlayerWin = state.nextMove != state.playerSide

    MaterialTheme {
        when (state.screen) {
            Screen.StartScreen -> {
                StartScreen(
                    state.playerSide,
                    state.gameVariant,
                    viewModel.sides,
                    viewModel.games,
                    viewModel::onGameSelect,
                    viewModel::onSideSelect
                )
            }
            Screen.GameScreen -> {
                GameScreen(
                    isPlayerWin,
                    state.gameState,
                    state.fields,
                    viewModel::onFieldSelect,
                    viewModel::onBackClick,
                )
            }
        }
    }
}
