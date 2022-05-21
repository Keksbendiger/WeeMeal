package de.darthkali.mocks

import de.darthkali.domain.models.enums.MealTime

object MealTimeMock {
    val randomMealTime = MealTime.getRandom()

    fun generateList(amount: Int): List<MealTime> {
        assert(amount >= 0)
        val mealTimeList: MutableList<MealTime> = mutableListOf()

        for (i in 1..amount) {
            mealTimeList.add(MealTime.getRandom())
        }
        return mealTimeList
    }
}
