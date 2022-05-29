package compose.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.Field
import compose.GameState
import compose.view.items.FieldItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameScreen(
    isWin: Boolean,
    gameState: GameState,
    fields: Array<Array<Field>>,
    onFieldSelect: (Int, Int) -> Unit,
    onOneMoreGame: () -> Unit,
) = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
) {
    GeneralField(fields, onFieldSelect)

    if (gameState != GameState.Unfinished) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text(text = winLooseTitle(isWin, gameState)) },
            text = { Text(text = winLooseMessage(isWin, gameState)) },
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

fun winLooseMessage(isWin: Boolean, gameState: GameState): String = when(gameState){
    GameState.Win -> {
        if (isWin) {
            "Congratulations!!! Luck is on your side, play one more game!"
        } else {
            "Do not be upset, luck will smile at you in the next game!"
        }
    }
    GameState.Draw -> {
        "Congratulations, it was a good fight"
    }
    else -> throw TODO()
}

fun winLooseTitle(isWin: Boolean, gameState: GameState): String = when(gameState) {
    GameState.Win -> {
        if (isWin) {
            "You win"
        } else {
            "You lost"
        }
    }
    GameState.Draw -> {
        "Draw"
    }
    else -> throw TODO()
}