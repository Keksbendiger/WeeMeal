package de.fhe.ai.weemeal.weeklistComponent

import de.fhe.ai.weemeal.domain.models.Meal
import java.util.Date

data class WeekListState(
    var weekdays: MutableList<WeekDay> ,
    var amountOfDaysAhead: Int = 0
)

data class WeekDay(
    val day: Date,
    val meals: List<Meal> = listOf()
)
