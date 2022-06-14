package de.fhe.ai.weemeal.common.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.common.theme.WeeMealTheme

@Composable
fun CustomChipButton(
    onClick: () -> Unit,
    text: String = "CustomChipButton",
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        elevation = 8.dp,
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colors.primaryVariant
    ) {
        TextButton(
            onClick = onClick,
            modifier = Modifier,
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .defaultMinSize(minWidth = 50.dp)
                    .padding(0.dp),
            )
        }
    }
}
