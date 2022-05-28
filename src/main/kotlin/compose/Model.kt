package compose

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

fun Array<Array<Field>>.changeFields(
    firstIndex: Int,
    secondIndex: Int,
    value: GameState
): Array<Array<Field>> = this.apply { this[firstIndex][secondIndex].state = value }

fun GameState.anotherState(): GameState = when (this) {
    GameState.Cross -> GameState.Zero
    GameState.Zero -> GameState.Cross
}

fun fieldsCheck(fields: Array<Array<Field>>): Boolean = //TODO(no win side case!!!)
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

fun Array<Array<Field>>.botMoveFields(
    gameVariant: GameVariant?,
    botSide: GameState?
): Array<Array<Field>> {
    if (gameVariant == null || botSide == null) {
        return this
    }

    return when(gameVariant) {
        GameVariant.EasyBot -> easyBotMovePosition(this, botSide)
        GameVariant.HardBot -> hardBotMovePosition(this, botSide)
        GameVariant.Single -> this
    }
}

fun easyBotMovePosition(fields: Array<Array<Field>>, state: GameState): Array<Array<Field>> {
    TODO()
}

fun hardBotMovePosition(fields: Array<Array<Field>>, state: GameState): Array<Array<Field>> {
    TODO()
}


