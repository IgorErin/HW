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

    val sides: List<GameState>
        get() {
            return fetchSides()
        }

    data class State(
        var screen: Screen,
        var gameVariant: GameVariant?,
        val fields: Array<Array<Field>>,
        var lastMove: GameState,
        var playerSide: GameState?,
        var gameOver: Boolean,
    )

    private fun initialState(): State = State(
        Screen.StartScreen,
        null,
        fetchFields(),
        GameState.Cross,
        null,
        false,
    )

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onFieldSelect(firstIndex: Int, secondIndex: Int) {
        var newFields = state.fields.changeFields(firstIndex, secondIndex, state.nextPlayerFieldState)


    }

    fun onGameSelect(value: GameVariant) = updateState {
        val firstBotMoveFields = when(state.playerSide) {
            GameState.Cross -> state.fields
            else -> fields.botMoveFields(value, state.playerSide?.anotherState())
        }

        copy(
            fields = firstBotMoveFields,
            gameVariant = value,
            screen = changeStartScreenToGameScreen(value, state.playerSide)
        )
    }

    fun onSideSelect(value: GameState) = updateState {
        val firstBotMoveFields = when(value) {
            GameState.Cross -> state.fields
            GameState.Zero -> fields.botMoveFields(state.gameVariant, value.anotherState())
        }

        copy(
            fields = firstBotMoveFields,
            playerSide = value,
            screen = changeStartScreenToGameScreen(state.gameVariant, value)
        )
    }

    fun onBackClick() = updateState { initialState() }
}