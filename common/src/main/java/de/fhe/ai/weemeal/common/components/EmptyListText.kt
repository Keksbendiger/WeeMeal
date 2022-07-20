package de.fhe.ai.weemeal.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.common.theme.WeeMealTheme

@Composable
fun EmptyListText(text: String = "Leere Liste...") {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6.copy(),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun EmptyListPreview() {
    WeeMealTheme {
        EmptyListText()
    }
}
