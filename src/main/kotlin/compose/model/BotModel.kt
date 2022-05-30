package compose.model

import compose.Field
import compose.GameFieldState
import compose.GameVariant

const val FIELD_SIZE = 3

fun Array<Array<Field>>.botMoveFields(gameVariant: GameVariant?, playerSide: GameFieldState?): Array<Array<Field>> {
    if (gameVariant == null || playerSide == null || this.indicesPairs(null).size == 0) {
        return this
    }

    return when (gameVariant) {
        GameVariant.EasyBot -> easyBotMovePosition(this, playerSide.nextState())
        GameVariant.HardBot -> hardBotMovePos(this, playerSide.nextState())
        GameVariant.Single -> this
    }
}

private fun easyBotMovePosition(fields: Array<Array<Field>>, state: GameFieldState): Array<Array<Field>> {
    val list = fields.indicesPairs(null)
    val index = list.indices.random()
    val position = list[index]

    return fields.changeField(position.first, position.second, state)
}

private fun hardBotMovePos(fields: Array<Array<Field>>, state: GameFieldState): Array<Array<Field>> = when {
    fields[1][1].state == null -> fields.changeField(1, 1, state)
    fields[1][1].state != state -> when {
        fields[0][2].state == null && fields[2][0].state != state -> fields.changeField(0, 2, state)
        fields[0][0].state == null && fields[2][2].state != state -> fields.changeField(0, 0, state)
        fields[2][2].state == null && fields[0][0].state != state -> fields.changeField(2, 2, state)
        fields[2][0].state == null && fields[0][2].state != state -> fields.changeField(2, 0, state)
        else -> easyBotMovePosition(fields, state)
    }
    else -> easyBotMovePosition(fields, state)
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
