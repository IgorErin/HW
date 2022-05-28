package compose

import androidx.compose.foundation.shape.ZeroCornerSize
import java.util.Arrays
import kotlin.concurrent.fixedRateTimer

fun fetchFields(): Array<Array<Field>> = Array(3) { firstIndex ->
    Array(3) { secondIndex ->
        Field(null, firstIndex, secondIndex)
    }
}

fun fetchGames(): List<GameVariant> = listOf(GameVariant.EasyBot, GameVariant.HardBot, GameVariant.Single)

fun fetchSides(): List<GameState> = listOf(GameState.Zero, GameState.Cross)

fun changeStartScreenToGameScreen(gameVariant: GameVariant?, side: GameState?): Screen {
    if (gameVariant != null && side != null) {
        return Screen.GameScreen
    }

    return Screen.StartScreen
}

fun Array<Array<Field>>.changeFields(firstIndex: Int, secondIndex: Int, value: GameState): Array<Array<Field>> {
    if (this[firstIndex][secondIndex].state != null) {
        return this
    }

    return this.apply { this[firstIndex][secondIndex].state = value } //TODO(work????)
}

fun nextMove(currentValue: GameState): GameState = when (currentValue) {
    GameState.Cross -> GameState.Zero
    GameState.Zero -> GameState.Cross
}

fun fieldsCheck(fields: Array<Array<Field>>): Boolean =
    checkLines(fields) || checkColumns(fields) || checkDiagonals(fields)

private fun checkLines(fields: Array<Array<Field>>): Boolean {
    for (subArray in fields) {
        if (checkSubArray(subArray)) return true
    }

    return false
}

private fun checkColumns(fields: Array<Array<Field>>): Boolean {
    for (columnIndex in fields.indices) {
        val subArray = fields.map { it[columnIndex] }.toTypedArray()

        if (checkSubArray(subArray)) return true
    }

    return false
}

private fun checkDiagonals(fields: Array<Array<Field>>): Boolean {
    val generalDiagonal = mutableListOf<Field>()
    val sideDiagonal = mutableListOf<Field>()

    for (i in fields.indices) {
        generalDiagonal.add(fields[i][i])
        sideDiagonal.add(fields[i][2 - i])
    }

    return checkSubArray(generalDiagonal.toTypedArray()) || checkSubArray(sideDiagonal.toTypedArray())
}
private fun checkSubArray(fields: Array<Field>): Boolean {
    val firstState = fields[0].state ?: return false

    return when {
        fields.count { it.state == firstState } == 3 -> true
        else -> false
    }
}

