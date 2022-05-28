package compose.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import compose.GameVariant


@Composable
fun WinScreen(
    gameVariant: GameVariant,
    playerIsWin: Boolean,
    onBackClick: ()-> Unit,
) = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text("message")

        Button(onBackClick) {
            Text("New game")
        }
    }
}
