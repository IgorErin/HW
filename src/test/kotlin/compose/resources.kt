package compose

val resourcesEmptyArray = Array(3) { Array(3) { Field(null) } }

val resourcesFilledInArrayWithoutWin = arrayOf(
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Zero), Field(GameFieldState.Cross)),
    arrayOf(Field(null), Field(GameFieldState.Zero), Field(null)),
    arrayOf(Field(GameFieldState.Zero), Field(null), Field(GameFieldState.Cross))
)

val resourcesFilledInArrayWithWinOnLine = arrayOf(
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Cross), Field(GameFieldState.Cross)),
    arrayOf(Field(null), Field(null), Field(null)),
    arrayOf(Field(GameFieldState.Zero), Field(GameFieldState.Cross), Field(GameFieldState.Cross))
)

val resourcesFilledInArrayWithWinOnColumn = arrayOf(
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Zero), Field(GameFieldState.Cross)),
    arrayOf(Field(GameFieldState.Cross), Field(null), Field(null)),
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Zero), Field(GameFieldState.Cross))
)

val resourcesFilledInArrayWithWinOnDiagonal = arrayOf(
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Zero), Field(GameFieldState.Cross)),
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Cross), Field(null)),
    arrayOf(Field(GameFieldState.Zero), Field(GameFieldState.Zero), Field(GameFieldState.Cross))
)

val resourcesFullFilledInArrayWithDraw = arrayOf(
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Zero), Field(GameFieldState.Cross)),
    arrayOf(Field(GameFieldState.Zero), Field(GameFieldState.Cross), Field(GameFieldState.Cross)),
    arrayOf(Field(GameFieldState.Zero), Field(GameFieldState.Cross), Field(GameFieldState.Zero))
)

fun Array<Array<Field>>.deepCopy(): Array<Array<Field>> = Array(this.size) { lineIndex ->
    Array(this[lineIndex].size) { columnIndex ->
        Field(this[lineIndex][columnIndex].state)
    }
}
