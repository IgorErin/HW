package compose.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import compose.Field
import compose.GameVariant
import compose.view.items.FieldItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameScreen(
    gameVariant: GameVariant?,
    fields: Array<Array<Field>>,
    onFieldSelect: (Int, Int) -> Unit,
) = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
) {
    val openGame = remember { mutableStateOf(true) }

    GeneralField(fields, onFieldSelect)

    if (openGame.value) {
        AlertDialog(
            onDismissRequest = {
                openGame.value = false
            },
            title = { Text(text = "Подтверждение действия") },
            text = { Text("Вы действительно хотите удалить выбранный элемент?") },
            buttons = {
                Button(
                    onClick = { openGame.value = false }
                ) {
                    Text("OK", fontSize = 22.sp)
                }
            }
        )
    }
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

