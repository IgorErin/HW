package compose

data class Field(var state: GameFieldState?)

enum class Screen {
    StartScreen, GameScreen
}

enum class GameFieldState {
    Cross, Zero
}

enum class GameVariant {
    EasyBot, HardBot, Single
}
