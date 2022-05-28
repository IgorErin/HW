package compose.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import compose.Field
import compose.GameVariant
import compose.view.items.FieldItem

@Composable
fun GameScreen(
    gameVariant: GameVariant?,
    fields: Array<Array<Field>>,
    onFieldSelect: (Int, Int) -> Unit,
) = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
) {
    GeneralField(fields, onFieldSelect)
}

@Composable
fun GeneralField(fields: Array<Array<Field>>, onFieldSelect: (Int, Int) -> Unit) {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        for (lineIndex in fields.indices) {
            Row {
                for (columnIndex in fields[lineIndex].indices) {
                    FieldItem(
                        fields[lineIndex][columnIndex],
                        onClick = { onFieldSelect(lineIndex, columnIndex) },
                    )
                }
            }
        }
    }
}

