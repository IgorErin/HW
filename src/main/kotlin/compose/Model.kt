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

fun Array<Array<Field>>.changeFields(firstIndex: Int, secondIndex: Int, value: GameState): Array<Array<Field>> {
    if (this[firstIndex][secondIndex].state != null) {
        return this
    }

    return this.apply { this[firstIndex][secondIndex] = Field(value, firstIndex, secondIndex) } //TODO(work????)
}

fun fieldsCheck(fields: Array<Array<Field>>): Boolean {
    return true
}

