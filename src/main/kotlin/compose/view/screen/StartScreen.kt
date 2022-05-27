package compose.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import compose.GameVariant
import compose.Side
import compose.view.items.GameItem
import compose.view.items.SideItem

@Composable
fun StartScreen(
    sides: List<Side>,
    games: List<GameVariant>,
    onSelectGameVariant: (GameVariant) -> Unit,
    onSelectSide: (Side) -> Unit
) = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
) {//TODO()
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("choose game")

        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            items(items = games) {
                GameItem(
                    it,
                    onClick = { onSelectGameVariant(it) },
                )
            }
        }

        Text("choose side")

        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            items(items = sides) {
                SideItem(
                    it,
                    onClick = { onSelectSide(it) },
                )
            }
        }
    }
}