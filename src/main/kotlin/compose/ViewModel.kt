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

    val sides: List<GameFieldState>
        get() {
            return fetchSides()
        }

    data class State(
        var screen: Screen,
        var gameVariant: GameVariant?,
        val fields: Array<Array<Field>>,
        var nextMove: GameFieldState,
        var playerSide: GameFieldState?,
        var gameState: Boolean,
        var count: Int
    )

    private fun initialState(): State = State(
        Screen.StartScreen,
        null,
        fetchFields(),
        GameFieldState.Cross,
        null,
        false,
        0
    )

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onFieldSelect(firstIndex: Int, secondIndex: Int) {
        val newFields = state.fields.changeFields(firstIndex, secondIndex, state.nextMove)

        return updateState {
            copy(nextMove = nextMove.nextState(), fields = newFields, gameState = fieldsCheck(fields))
        }
    }
    fun onGameSelect(value: GameVariant) = updateState {
        copy(
            fields = fields.firstMoveFields(playerSide, value),
            gameVariant = value,
            screen = changeStartScreenToGameScreen(value, state.playerSide),
            nextMove = nextMove.setNextMove(state.playerSide, value)
        )
    }
    fun onSideSelect(value: GameFieldState) = updateState {
        copy(
            fields = fields.firstMoveFields(value, gameVariant),
            playerSide = value,
            screen = changeStartScreenToGameScreen(gameVariant, value),
            nextMove = nextMove.setNextMove(value, gameVariant)
        )
    }
    fun onBackClick() = updateState { initialState() }
}