package compose

data class Field(var state: GameFiledState?, val id: Int)

enum class Screen {
    StartScreen, GameScreen, WinScreen
}

enum class GameFiledState {
    Cross, Zero, Empty
}

enum class GameVariant {
    EasyBot, HardBot, Single
}

enum class MoveState {
    Cross, Zero
}

enum class Side {
    Cross, Zero
}