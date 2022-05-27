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
        var gameVariant: GameVariant,
        val fields: List<Field>,
        var move: MoveState,
        var side: Side
    )

    private fun initialState(): State = State(
        Screen.StartScreen,
        GameVariant.NotInit,
        fetchFields(),
        MoveState.Cross,
        Side.NotInit
    )

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }



    fun onFieldSelect(id: Int) = updateState {
        TODO()
    }

    fun onGameSelect(value: GameVariant) = updateState {
        copy(gameVariant = value, screen = changeScreen()) //TODO()
    }

    fun onSideSelect(value: Side) = updateState {
        copy(side = value, screen = changeScreen()) //TODO()
    }

}