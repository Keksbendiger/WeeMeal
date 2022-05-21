package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.domain.enums.MealTime

object MealTimeMock {

    fun generateList(amount: Int): List<MealTime> {
        assert(amount >= 0)
        val mealTimeList: MutableList<MealTime> = mutableListOf()

        for (i in 1..amount) {
            mealTimeList.add(MealTime.getRandom())
        }
        return mealTimeList
    }
}
