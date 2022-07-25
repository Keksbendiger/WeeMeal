package de.fhe.ai.weemeal.domain.enums

import java.security.SecureRandom

enum class CookColor(val color: String) {

    TRANSPARENT("#00000000"),
    YELLOW("#FFFFFF00"),
    GREEN("#FF00FF00"),
    RED("#FFFF0000"),
    BLUE("#FF0000FF");

    companion object {
        fun getRandom(): CookColor {
            return CookColor.values()[SecureRandom().nextInt(CookColor.values().size)]
        }
    }
}
