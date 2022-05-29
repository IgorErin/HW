package compose

data class Field(var state: GameState?)

enum class Screen {
    StartScreen, GameScreen
}

enum class GameState {
    Cross, Zero
}

enum class GameVariant {
    EasyBot, HardBot, Single
}
