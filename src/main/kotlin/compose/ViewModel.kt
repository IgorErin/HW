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
        var nextFieldState: GameState,
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
        val newFields = state.fields.changeFields(firstIndex, secondIndex, state.nextFieldState)

        return updateState {
            copy(
                nextFieldState = nextFieldState.anotherState(),
                fields = newFields.botMoveFields(gameVariant, state.playerSide?.anotherState()),
                gameOver = fieldsCheck(fields)
            )
        }
    }

    fun onGameSelect(value: GameVariant) = updateState {
        copy(
            gameVariant = value,
            screen = changeStartScreenToGameScreen(value, state.playerSide),
            fields = fields.botMoveFields(gameVariant, state.playerSide?.anotherState())
        )
    }

    fun onSideSelect(value: GameState) = updateState {
        copy(
            playerSide = value,
            screen = changeStartScreenToGameScreen(state.gameVariant, value),
            fields = fields.botMoveFields(gameVariant, value.anotherState())
        )
    }

    fun onBackClick() = updateState { initialState() }


    /*fun onFieldSelect(firstIndex: Int, secondIndex: Int) {
        val newFields = state.fields.changeFields(firstIndex, secondIndex, state.nextFieldState)

        return updateState {
            copy(nextFieldState = nextMove(nextFieldState), fields = newFields, isWin = fieldsCheck(fields))
        }
    }

    fun onGameSelect(value: GameVariant) = updateState {
        copy(gameVariant = value, screen = changeStartScreenToGameScreen(value, state.playerSide))
    }

    fun onSideSelect(value: GameState) = updateState {
        copy(playerSide = value, screen = changeStartScreenToGameScreen(state.gameVariant, value),)
    }

    fun onBackClick() = updateState { initialState() }*/
}