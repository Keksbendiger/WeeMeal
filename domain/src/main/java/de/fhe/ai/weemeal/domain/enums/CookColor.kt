package de.fhe.ai.weemeal.domain.enums

import androidx.compose.ui.graphics.Color
import java.security.SecureRandom

enum class CookColor(val color: Color) {
    TRANSPARENT(Color.Transparent),
    YELLOW(Color.Yellow),
    GREEN(Color.Green),
    RED(Color.Red),
    BLUE(Color.Blue);

    companion object {
        fun getRandom(): CookColor {
            return CookColor.values()[SecureRandom().nextInt(CookColor.values().size)]
        }
    }
}
