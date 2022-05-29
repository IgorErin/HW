package compose.view.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import compose.GameVariant

@Suppress("FunctionNaming")
@Composable
fun GameItem(
    isPressed: Boolean,
    game: GameVariant,
    onClick: () -> Unit
) = Row(Modifier.clickable(onClick = onClick)) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = !isPressed
    ) {
        Text(
            buildAnnotatedString {
                append(printGameName(game))
            },
            modifier = Modifier.padding(10.dp)
        )
    }
}

fun printGameName(game: GameVariant): String = when (game) {
    GameVariant.EasyBot -> "Easy bot"
    GameVariant.HardBot -> "Hard bot"
    GameVariant.Single -> "Single "
}
