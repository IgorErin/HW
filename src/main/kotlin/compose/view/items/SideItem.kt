package compose.view.items

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import compose.GameFieldState

@Suppress("FunctionNaming")
@Composable
fun SideItem(
    isPressed: Boolean,
    side: GameFieldState,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        enabled = !isPressed
    ) {
        Text(
            buildAnnotatedString {
                append("$side") // TODO()
            },
            modifier = Modifier.padding(10.dp)
        )
    }
}
