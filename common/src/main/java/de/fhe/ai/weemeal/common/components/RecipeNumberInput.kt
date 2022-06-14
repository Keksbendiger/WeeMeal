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
fun RecipeNumberInput(
    value: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(1.dp)
            .width(50.dp)
            .height(24.dp)
            .background(color = LightColors.primaryVariant),
        textStyle = TextStyle(color = LightColors.onBackground),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}