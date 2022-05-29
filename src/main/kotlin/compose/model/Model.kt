package compose

const val FIELD_SIZE = 3
fun fetchFields(): Array<Array<Field>> = Array(FIELD_SIZE) { Array(FIELD_SIZE) { Field(null) } }

fun fetchGames(): List<GameVariant> = listOf(GameVariant.EasyBot, GameVariant.HardBot, GameVariant.Single)

fun fetchSides(): List<GameFieldState> = listOf(GameFieldState.Zero, GameFieldState.Cross)

fun changeStartScreenToGameScreen(side: GameFieldState?, gameVariant: GameVariant?): Screen {
    if (gameVariant != null && side != null) {
        return Screen.GameScreen
    }

    return Screen.StartScreen
}

fun Array<Array<Field>>.changeFields(
    firstIndex: Int,
    secondIndex: Int,
    value: GameFieldState
): Array<Array<Field>> = this.apply { this[firstIndex][secondIndex].state = value }

fun GameFieldState.nextState(): GameFieldState = when (this) {
    GameFieldState.Cross -> GameFieldState.Zero
    GameFieldState.Zero -> GameFieldState.Cross
}

fun fieldsCheck(fields: Array<Array<Field>>): GameState = when {
    checkLines(fields) || checkColumns(fields) || checkDiagonals(fields) -> GameState.Win
    emptyPairs(fields).isEmpty() -> GameState.Draw
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

fun Array<Array<Field>>.botMoveFields(gameVariant: GameVariant?, playerSide: GameFieldState?): Array<Array<Field>> {
    if (gameVariant == null || playerSide == null || emptyPairs(this).size == 0) {
        return this
    }

    return when (gameVariant) {
        GameVariant.EasyBot -> easyBotMovePosition(this, playerSide.nextState())
        GameVariant.HardBot -> hardBotMovePosition(this, playerSide.nextState())
        GameVariant.Single -> this
    }
}

private fun easyBotMovePosition(fields: Array<Array<Field>>, state: GameFieldState): Array<Array<Field>> {
    val list = emptyPairs(fields)
    val index = list.indices.random()
    val position = list[index]

    return fields.changeFields(position.first, position.second, state)
}

fun emptyPairs(fields: Array<Array<Field>>): MutableList<Pair<Int, Int>> {
    val list = mutableListOf<Pair<Int, Int>>()

    for (lineIndex in fields.indices) {
        for (columnIndex in fields[lineIndex].indices) {
            if (fields[lineIndex][columnIndex].state == null) {
                list.add(Pair(lineIndex, columnIndex))
            }
        }
    }

    return list
}

private fun hardBotMovePosition(fields: Array<Array<Field>>, state: GameFieldState): Array<Array<Field>> {
    TODO()
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

fun Array<Array<Field>>.firstMoveFields(playerSide: GameFieldState?, gameVariant: GameVariant?): Array<Array<Field>> {
    if (playerSide == null || gameVariant == null) {
        return this
    }

    return when (playerSide) {
        GameFieldState.Cross -> this
        else -> this.botMoveFields(gameVariant, playerSide)
    }
}

fun GameFieldState.nextMoveChange(fieldCheck: GameState, gameVariant: GameVariant?): GameFieldState {
    gameVariant ?: throw NullPointerException("Game variant not selected")

    if (fieldCheck != GameState.Unfinished || gameVariant == GameVariant.Single) {
        return this.nextState()
    }

    return this
}
