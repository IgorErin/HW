package compose.view.items

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import compose.GameFieldState

@Composable
fun SideItem(
    side: GameFieldState,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(
            buildAnnotatedString {
                append("$side") //TODO()
            },
            modifier = Modifier.padding(10.dp)
        )
    }
}

fun printSideName(side: GameFieldState): String = when(side) {
    GameFieldState.Zero -> "zero"
    GameFieldState.Cross -> "cross"
}