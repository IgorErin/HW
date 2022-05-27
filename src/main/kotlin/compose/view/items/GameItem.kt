package compose.view.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import compose.GameVariant

@Composable
fun GameItem(
    game: GameVariant,
    onClick: () -> Unit
)  = Row(Modifier.clickable(onClick = onClick)) {
    Button(onClick = onClick) {
        Text(
            buildAnnotatedString {
                append("$game")
            },
            modifier = Modifier.padding(10.dp)
        )
    }
}