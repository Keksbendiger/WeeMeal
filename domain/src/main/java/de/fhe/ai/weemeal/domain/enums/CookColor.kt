package de.fhe.ai.weemeal.domain.enums

import java.security.SecureRandom

enum class CookColor(val color: String) {

    TRANSPARENT("0x00000000"),
    YELLOW("0xFFFFFF00"),
    GREEN("0xFF00FF00"),
    RED("0xFFFF0000"),
    BLUE("0xFF0000FF");

    companion object {
        fun getRandom(): CookColor {
            return CookColor.values()[SecureRandom().nextInt(CookColor.values().size)]
        }
    }
}
