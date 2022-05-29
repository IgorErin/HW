package compose.model

import compose.FIELD_SIZE
import compose.Field
import compose.GameFieldState
import compose.GameVariant

fun fetchFields(): Array<Array<Field>> = Array(FIELD_SIZE) { Array(FIELD_SIZE) { Field(null) } }

fun fetchGames(): List<GameVariant> = listOf(GameVariant.EasyBot, GameVariant.HardBot, GameVariant.Single)

fun fetchSides(): List<GameFieldState> = listOf(GameFieldState.Zero, GameFieldState.Cross)
