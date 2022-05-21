package de.fhe.ai.weemeal.common.formats

import java.security.SecureRandom

enum class MeasurementUnit(val value:String) {
    EMPTY(""),

    GRAM("g"),

    TABLESPOON("EL"),
    TEASPOON("TL"),

    BUNCH ("Bund"),
    PINCH("Prise"),

    MILLILITER("ml"),
    LITER("L");

    companion object {
        fun getRandom():MeasurementUnit{
            return MeasurementUnit.values()[SecureRandom().nextInt(MeasurementUnit.values().size)];
        }
    }
}