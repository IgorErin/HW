package compose.model

import compose.GameFieldState
import compose.GameVariant
import compose.Screen

fun changeStartScreenToGameScreen(side: GameFieldState?, gameVariant: GameVariant?): Screen {
    if (gameVariant != null && side != null) {
        return Screen.GameScreen
    }

    return Screen.StartScreen
}
