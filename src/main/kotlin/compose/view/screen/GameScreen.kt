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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.Field
import compose.GameVariant
import compose.view.items.FieldItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameScreen(
    isWin: Boolean,
    gameOver: Boolean,
    fields: Array<Array<Field>>,
    onFieldSelect: (Int, Int) -> Unit,
    onOneMoreGame: () -> Unit
) = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
) {
    GeneralField(fields, onFieldSelect)

    if (gameOver) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = winLooseTitle(isWin)) },
            text = { Text(text = winLooseMessage(isWin)) },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { onOneMoreGame() }
                    ) {
                        Text("Menu", fontSize = 22.sp)
                    }
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

fun winLooseMessage(isWin: Boolean): String {
    if (isWin) {
        return "Congratulations !!! Luck is on your side play one more game"
    }

    return "Do not be upset, luck will smile at you in the next game !"
}

fun winLooseTitle(isWin: Boolean): String {
    if (isWin) {
        return "You win"
    }

    return "You lost"
}
