package compose

import compose.model.changeField
import compose.model.fieldsCheck
import compose.model.indicesPairs
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ModelKtTest {
    @BeforeEach
    fun initFields() {
        emptyFieldArray = resourcesEmptyArray.deepCopy()
        filledInArrayWithoutWin = resourcesFilledInArrayWithoutWin.deepCopy()
        filledInArrayWithWinOnLine = resourcesFilledInArrayWithWinOnLine.deepCopy()
        filledInArrayWithWinOnColumn = resourcesFilledInArrayWithWinOnColumn.deepCopy()
        filledInArrayWithWinOnDiagonal = resourcesFilledInArrayWithWinOnDiagonal.deepCopy()
        fullFilledInArrayWithDraw = resourcesFullFilledInArrayWithDraw.deepCopy()
    }
    @Test
    fun `fetch sides test`() {
        assertEquals(listOf(GameFieldState.Zero, GameFieldState.Cross), ViewModel().sides)
    }

    @Test
    fun `fetch games test`() {
        assertEquals(listOf(GameVariant.EasyBot, GameVariant.HardBot, GameVariant.Single), ViewModel().games)
    }

    @Test
    fun `fields change`() {
        assertEquals(null, filledInArrayWithoutWin[2][1].state)
        filledInArrayWithoutWin.changeField(2, 1, GameFieldState.Cross)
        assertEquals(GameFieldState.Cross, filledInArrayWithoutWin[2][1].state)
    }

    @ParameterizedTest
    @MethodSource("fieldsCheckTest")
    fun `fieldsCheck test`(fields: Array<Array<Field>>, gameState: GameState) {
        assertEquals(gameState, fieldsCheck(fields))
    }

    @ParameterizedTest
    @MethodSource("emptyPairMethodTest")
    fun `empty pair method test`(fields: Array<Array<Field>>, number: Int) {
        assertEquals(number, fields.indicesPairs(null).size)
    }

    companion object {
        private var emptyFieldArray = resourcesEmptyArray
        private var filledInArrayWithoutWin = resourcesFilledInArrayWithoutWin
        private var filledInArrayWithWinOnLine = resourcesFilledInArrayWithWinOnLine
        private var filledInArrayWithWinOnColumn = resourcesFilledInArrayWithWinOnColumn
        private var filledInArrayWithWinOnDiagonal = resourcesFilledInArrayWithWinOnDiagonal
        private var fullFilledInArrayWithDraw = resourcesFullFilledInArrayWithDraw

        @JvmStatic
        fun fieldsCheckTest() = listOf(
            Arguments.of(filledInArrayWithoutWin, GameState.Unfinished),
            Arguments.of(filledInArrayWithWinOnLine, GameState.Win),
            Arguments.of(filledInArrayWithWinOnColumn, GameState.Win),
            Arguments.of(filledInArrayWithWinOnDiagonal, GameState.Win),
            Arguments.of(fullFilledInArrayWithDraw, GameState.Draw)
        )

        @JvmStatic
        fun emptyPairMethodTest() = listOf(
            Arguments.of(emptyFieldArray, 9),
            Arguments.of(filledInArrayWithoutWin, 3),
            Arguments.of(filledInArrayWithWinOnLine, 3),
            Arguments.of(filledInArrayWithWinOnColumn, 2),
            Arguments.of(filledInArrayWithWinOnDiagonal, 1),
            Arguments.of(fullFilledInArrayWithDraw, 0)
        )
    }
}
