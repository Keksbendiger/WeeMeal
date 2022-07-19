package de.fhe.ai.weemeal.common.functions

import java.util.Calendar
import java.util.Date

fun getDaysAhead(daysAhead: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, daysAhead)

    return calendar.time
}