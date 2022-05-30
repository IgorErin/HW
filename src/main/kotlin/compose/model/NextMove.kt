package compose.model

import compose.Field
import compose.GameFieldState
import compose.GameState
import compose.GameVariant

fun Array<Array<Field>>.changeField(
    firstIndex: Int,
    secondIndex: Int,
    value: GameFieldState
): Array<Array<Field>> = this.apply { this[firstIndex][secondIndex].state = value }

fun GameFieldState.nextMoveChange(fieldCheck: GameState, gameVariant: GameVariant?): GameFieldState {
    gameVariant ?: throw NullPointerException("Game variant not selected")

    if (fieldCheck != GameState.Unfinished || gameVariant == GameVariant.Single) {
        return this.nextState()
    }

    return this
}

fun GameFieldState.setNextMove(value: GameFieldState?, gameVariant: GameVariant?): GameFieldState {
    if (value == null || gameVariant == null) {
        return this
    }

    return when (gameVariant) {
        GameVariant.Single -> GameFieldState.Cross
        else -> value
    }
}

fun GameFieldState.nextState(): GameFieldState = when (this) {
    GameFieldState.Cross -> GameFieldState.Zero
    GameFieldState.Zero -> GameFieldState.Cross
}

fun Array<Array<Field>>.firstMoveFields(playerSide: GameFieldState?, gameVariant: GameVariant?): Array<Array<Field>> {
    if (playerSide == null || gameVariant == null) {
        return this
    }

    return when (playerSide) {
        GameFieldState.Cross -> this
        else -> this.botMoveFields(gameVariant, playerSide)
    }
}
