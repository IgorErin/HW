package compose

fun fetchFields(): List<Field> = List(9) { Field(GameFiledState.NotInit, it) }

fun fetchGames(): List<GameVariant> = listOf(GameVariant.EasyBot, GameVariant.HardBot, GameVariant.Single)

fun fetchSides(): List<Side> = listOf(Side.Zero, Side.Cross)