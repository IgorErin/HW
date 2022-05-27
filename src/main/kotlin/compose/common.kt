package compose

data class Field(var state: GameFiledState, val id: Int)

enum class Screen {
    StartScreen, GameScreen, WinScreen
}

enum class GameFiledState {
    Cross, Zero, NotInit
}

enum class GameVariant {
    EasyBot, HardBot, Single, NotInit
}

enum class MoveState {
    Cross, Zero
}

enum class Side {
    Cross, Zero, NotInit
}