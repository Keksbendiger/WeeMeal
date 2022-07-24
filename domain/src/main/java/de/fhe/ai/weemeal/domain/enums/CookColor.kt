package de.fhe.ai.weemeal.domain.enums

import android.graphics.Color
import java.security.SecureRandom

enum class CookColor(/*val color: String*/ val color: Int) {

    //TRANSPARENT("0x00000000"),
    TRANSPARENT(Color.TRANSPARENT),
    //YELLOW("0xFFFFFF00"),
    YELLOW(Color.YELLOW),
    //GREEN("0xFF00FF00"),
    GREEN(Color.GREEN),
    //RED("0xFFFF0000"),
    RED(Color.RED),
    //BLUE("0xFF0000FF");
    BLUE(Color.BLUE);

    companion object {
        fun getRandom(): CookColor {
            return CookColor.values()[SecureRandom().nextInt(CookColor.values().size)]
        }

        fun getNext(old: CookColor): CookColor {
            return when(old) {
                TRANSPARENT -> YELLOW
                YELLOW -> GREEN
                GREEN -> RED
                RED -> BLUE
                BLUE -> TRANSPARENT
            }
        }

        fun getPrevious(old: CookColor): CookColor {
            return when(old) {
                BLUE -> RED
                RED -> GREEN
                GREEN -> YELLOW
                YELLOW -> TRANSPARENT
                TRANSPARENT -> BLUE
            }
        }
    }

}
