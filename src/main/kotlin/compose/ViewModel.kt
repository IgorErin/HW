package compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import compose.model.botMoveFields
import compose.model.changeFields
import compose.model.changeStartScreenToGameScreen
import compose.model.fetchFields
import compose.model.fetchGames
import compose.model.fetchSides
import compose.model.fieldsCheck
import compose.model.firstMoveFields
import compose.model.nextMoveChange
import compose.model.setNextMove

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
        var gameState: GameState,
        var count: Int
    )

    private fun initialState(): State = State(
        Screen.StartScreen,
        null,
        fetchFields(),
        GameFieldState.Cross,
        null,
        GameState.Unfinished,
        0
    )

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }

    fun onFieldSelect(firstIndex: Int, secondIndex: Int) {
        val newFields = state.fields.changeFields(firstIndex, secondIndex, state.nextMove)
        val newGameState = fieldsCheck(newFields)
        val botMoveFields = newFields.botMoveFields(state.gameVariant, state.playerSide)

        return updateState {
            copy(
                nextMove = nextMove.nextMoveChange(newGameState, state.gameVariant),
                fields = botMoveFields,
                gameState = fieldsCheck(botMoveFields),
                count = count + 1
            )
        }
    }
    fun onGameSelect(value: GameVariant) = updateState {
        copy(
            fields = fields.firstMoveFields(playerSide, value),
            gameVariant = value,
            screen = changeStartScreenToGameScreen(state.playerSide, value),
            nextMove = nextMove.setNextMove(state.playerSide, value)
        )
    }
    fun onSideSelect(value: GameFieldState) = updateState {
        copy(
            fields = fields.firstMoveFields(value, gameVariant),
            playerSide = value,
            screen = changeStartScreenToGameScreen(value, gameVariant),
            nextMove = nextMove.setNextMove(value, gameVariant)
        )
    }
    fun onBackClick() = updateState { initialState() }
}
