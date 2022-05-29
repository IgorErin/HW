package compose

val emptyArray = Array(3) { Array(3) { Field(null) } }

val filledInArrayWithoutWin = arrayOf(
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Zero), Field(GameFieldState.Cross)),
    arrayOf(Field(null), Field(GameFieldState.Zero), Field(null)),
    arrayOf(Field(GameFieldState.Zero), Field(null), Field(GameFieldState.Cross))
)

val filledInArrayWithWinOnLine = arrayOf(
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Cross), Field(GameFieldState.Cross)),
    arrayOf(Field(null), Field(null), Field(null)),
    arrayOf(Field(GameFieldState.Zero), Field(GameFieldState.Cross), Field(GameFieldState.Cross))
)

val filledInArrayWithWinOnColumn = arrayOf(
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Zero), Field(GameFieldState.Cross)),
    arrayOf(Field(GameFieldState.Cross), Field(null), Field(null)),
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Zero), Field(GameFieldState.Cross))
)

val filledInArrayWithWinOnDiagonal = arrayOf(
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Zero), Field(GameFieldState.Cross)),
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Cross), Field(null)),
    arrayOf(Field(GameFieldState.Zero), Field(GameFieldState.Zero), Field(GameFieldState.Cross))
)

val fullFilledInArrayWithDraw = arrayOf(
    arrayOf(Field(GameFieldState.Cross), Field(GameFieldState.Zero), Field(GameFieldState.Cross)),
    arrayOf(Field(GameFieldState.Zero), Field(GameFieldState.Cross), Field(GameFieldState.Cross)),
    arrayOf(Field(GameFieldState.Zero), Field(GameFieldState.Cross), Field(GameFieldState.Zero))
)
