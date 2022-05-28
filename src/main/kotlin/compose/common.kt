package compose

data class Field(var state: GameState?, val firstIndex: Int, val secondIndex: Int)

enum class Screen {
    StartScreen, GameScreen, WinScreen
}

enum class GameState {
    Cross, Zero
}

enum class GameVariant {
    EasyBot, HardBot, Single
}
