package compose.view.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import compose.Field

@Composable
internal fun FieldItem(
    field: Field,
    onClick: () -> Unit
) = Row(Modifier.clickable(onClick = onClick)) {
    Button(onClick = onClick) {
        Text(
            buildAnnotatedString {
                append("${field.state}")
                withStyle(style = SpanStyle(color = androidx.compose.ui.graphics.Color.Companion.LightGray)) {}  //TODO()
            },
            modifier = Modifier.padding(10.dp)
        )
    }
}