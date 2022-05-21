package de.fhe.ai.weemeal.domain.models.enums

import java.security.SecureRandom

enum class MealTime(val mealTimeName: String) {
    BREAKFAST("Frühstück"),
    LUNCH("Mittag"),
    DINNER("Abendbrot");

    companion object {
        fun getRandom(): MealTime {
            return MealTime.values()[SecureRandom().nextInt(MealTime.values().size)]
        }
    }
}
