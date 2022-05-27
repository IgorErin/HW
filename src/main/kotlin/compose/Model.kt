package compose

fun fetchFields(): List<Field> = List(9) { Field(GameFiledState.Empty, it) }

fun fetchGames(): List<GameVariant> = listOf(GameVariant.EasyBot, GameVariant.HardBot, GameVariant.Single)

fun fetchSides(): List<Side> = listOf(Side.Zero, Side.Cross)

fun changeStartScreenToGameScreen(gameVariant: GameVariant?, side: Side?): Screen {
    if (gameVariant != null && side != null) {
        return Screen.GameScreen
    }

    return Screen.StartScreen
}

fun List<Field>.changeFields(id: Int, value: GameFiledState): List<Field> {
    if (this[id].state != GameFiledState.Empty) {
        return this
    }

    return this.map { it.findAndChangeField(id, value) }
}

private fun Field.findAndChangeField(id: Int, value: GameFiledState): Field {
    if (this.id == id) {
        return Field(value, id)
    }

    return this
}

