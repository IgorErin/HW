package compose.model

import compose.Field
import compose.GameFieldState
import compose.GameState

fun fieldsCheck(fields: Array<Array<Field>>): GameState = when {
    checkLines(fields) || checkColumns(fields) || checkDiagonals(fields) -> GameState.Win
    fields.indicesPairs(null).isEmpty() -> GameState.Draw
    else -> GameState.Unfinished
}

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
        fields.count { it.state == firstState } == FIELD_SIZE -> true
        else -> false
    }
}

fun Array<Array<Field>>.indicesPairs(gameFieldState: GameFieldState?): MutableList<Pair<Int, Int>> {
    val list = mutableListOf<Pair<Int, Int>>()

    for (lineIndex in this.indices) {
        for (columnIndex in this[lineIndex].indices) {
            if (this[lineIndex][columnIndex].state == gameFieldState) {
                list.add(Pair(lineIndex, columnIndex))
            }
        }
    }

    return list
}

