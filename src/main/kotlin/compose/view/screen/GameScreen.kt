package compose.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import compose.Field
import compose.GameVariant
import compose.view.items.FieldItem

@Composable
fun GameScreen(
    gameVariant: GameVariant?,
    fields: List<Field>,
    onFieldSelect: (Int) -> Unit,
) = Box(contentAlignment = Alignment.Center) { //TODO()
    Box {
        Column() {
            Text(
                buildAnnotatedString {
                    append(gameVariant?.toString() ?: TODO())
                    withStyle(style = SpanStyle(color = androidx.compose.ui.graphics.Color.LightGray)) {}
                }
            )
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            for (i in 0..2) {
                Row {
                    for (j in 0..2) {
                        FieldItem(
                            fields[i * 3 + j],
                            onClick = { onFieldSelect(fields[i * 3 + j].id) },
                        )
                    }
                }
            }
        }
    }
}
