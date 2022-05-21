package de.darthkali.domain.models.enums

enum class Mealtime(val mealTimeName: String) {
    BREAKFAST("Frühstück"),
    LUNCH("Mittag"),
    DINNER("Abendbrot");

    companion object {
        fun Mealtime.getName(): String {
            return this.mealTimeName
        }
    }
}
