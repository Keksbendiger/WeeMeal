package de.fhe.ai.weemeal.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import de.fhe.ai.weemeal.common.theme.LightColors

@Composable
fun RecipeStringInput(
    value: String,
    onValueChange: (String) -> Unit,
    wide: Boolean = false,
    modifier: Modifier = Modifier
) {
    var width = 50.dp
    if (wide) width = 220.dp

    BasicTextField(
        value = value,
        onValueChange = { newValue -> onValueChange(newValue) },
        modifier = modifier
            .padding(1.dp)
            .width(width)
            .height(24.dp)
            .background(color = LightColors.primaryVariant),
        textStyle = TextStyle(color = LightColors.onBackground),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}
