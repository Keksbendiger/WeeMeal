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

        fun getNext(old: CookColor): CookColor {
            return when (old) {
                TRANSPARENT -> YELLOW
                YELLOW -> GREEN
                GREEN -> RED
                RED -> BLUE
                BLUE -> TRANSPARENT
            }
        }

        fun getPrevious(old: CookColor): CookColor {
            return when (old) {
                BLUE -> RED
                RED -> GREEN
                GREEN -> YELLOW
                YELLOW -> TRANSPARENT
                TRANSPARENT -> BLUE
            }
        }
    }
}
