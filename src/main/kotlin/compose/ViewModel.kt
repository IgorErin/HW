package compose

import androidx.compose.runtime.Updater
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

    val sides: List<GameState>
        get() {
            return fetchSides()
        }

    data class State(
        var screen: Screen,
        var gameVariant: GameVariant?,
        val fields: Array<Array<Field>>,
        var nextFieldState: GameState,
        var side: GameState?
    )

    private fun initialState(): State = State(
        Screen.StartScreen,
        null,
        fetchFields(),
        GameState.Cross,
        null
    )

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onFieldSelect(firstIndex: Int, secondIndex: Int) {
        println("$firstIndex, $secondIndex")
        val newFields = state.fields.changeFields(firstIndex, secondIndex, state.nextFieldState)
        //return updateState { copy(fields = fields.changeFields(id, nextFieldState)) }
        if (fieldsCheck(newFields)) {
            return updateState { copy(screen = Screen.WinScreen) }
        }

        return updateState { copy( fields = newFields) }
    }

    fun onGameSelect(value: GameVariant) = updateState {
        copy(gameVariant = value, screen = changeStartScreenToGameScreen(value, state.side))
    }

    fun onSideSelect(value: GameState) = updateState {
        copy(side = value, screen = changeStartScreenToGameScreen(state.gameVariant, value))
    }

    fun onBackClick() = updateState { initialState() }
}