package de.fhe.ai.weemeal.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TextAndIconButton(text: String, icon: ImageVector, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colors.primary)
                .align(Alignment.Center)
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = text, color = MaterialTheme.colors.onPrimary)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(icon, "", tint = MaterialTheme.colors.onPrimary)
            }
        }
    }
}
