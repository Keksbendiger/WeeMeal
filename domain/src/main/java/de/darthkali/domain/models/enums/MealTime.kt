package de.darthkali.domain.models.enums

import java.security.SecureRandom
import java.util.Locale

enum class MealTime(val mealTimeName: String) {
    BREAKFAST("Frühstück"),
    LUNCH("Mittag"),
    DINNER("Abendbrot");

    companion object {
        fun getRandom():MealTime{
            return MealTime.values()[SecureRandom().nextInt(MealTime.values().size)];
        }
    }




}
