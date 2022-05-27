package compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    val games: List<GameVariant>
        get() {
            return fetchGames()
        }

    val sides: List<Side>
        get() {
            return fetchSides()
        }

    data class State(
        var screen: Screen,
        var gameVariant: GameVariant?,
        val fields: List<Field>,
        var nextFieldState: GameFiledState,
        var side: Side?
    )

    private fun initialState(): State = State(
        Screen.StartScreen,
        null,
        fetchFields(),
        GameFiledState.Cross,
        null
    )

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onFieldSelect(id: Int) = updateState {
        copy(fields = fields.changeFields(id, nextFieldState))
    }

    fun onGameSelect(value: GameVariant) = updateState {
        copy(gameVariant = value, screen = changeStartScreenToGameScreen(value, state.side))
    }

    fun onSideSelect(value: Side) = updateState {
        copy(side = value, screen = changeStartScreenToGameScreen(state.gameVariant, value))
    }
}