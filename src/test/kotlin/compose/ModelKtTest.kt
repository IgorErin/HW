package compose

import compose.model.fieldsCheck
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import kotlin.test.assertEquals

internal class ModelKtTest {
    @Test
    fun `fetch sides test`() {
        assertEquals(listOf(GameFieldState.Zero, GameFieldState.Cross), ViewModel().sides)
    }

    @Test
    fun `fetch games test`() {
        assertEquals(listOf(GameVariant.EasyBot, GameVariant.HardBot, GameVariant.Single), ViewModel().games)
    }

    @ParameterizedTest
    @MethodSource("fieldsCheckTest")
    fun `fieldsCheck test`(fields: Array<Array<Field>>, gameState: GameState) {
        assertEquals(gameState, fieldsCheck(fields))
    }

    /*@ParameterizedTest
    @MethodSource("emptyPairMethodTest")
    fun `empty pair method test`(fields: Array<Array<Field>>, number: Int) {
        assertEquals(number, emptyPairs(fields).size)
    }*/

    companion object {
        @JvmStatic
        fun fieldsCheckTest() = listOf(
            Arguments.of(filledInArrayWithoutWin, GameState.Unfinished),
            Arguments.of(filledInArrayWithWinOnLine, GameState.Win),
            Arguments.of(filledInArrayWithWinOnColumn, GameState.Win),
            Arguments.of(filledInArrayWithWinOnDiagonal, GameState.Win),
            Arguments.of(fullFilledInArrayWithDraw, GameState.Draw)
        )

        /*@JvmStatic
        fun emptyPairMethodTest() = listOf(
            Arguments.of(emptyArray, 9),
            Arguments.of(filledInArrayWithoutWin, 3),
            Arguments.of(filledInArrayWithWinOnLine, 3),
            Arguments.of(filledInArrayWithWinOnColumn, 2),
            Arguments.of(filledInArrayWithWinOnDiagonal, 1),
            Arguments.of(fullFilledInArrayWithDraw, 0)
        )*/
    }
}
