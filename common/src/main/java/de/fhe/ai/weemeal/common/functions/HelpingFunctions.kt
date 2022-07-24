package de.fhe.ai.weemeal.common.functions

import java.util.Calendar
import java.util.Date

fun getDaysAhead(daysAhead: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, daysAhead)

    return calendar.time
}

fun monthName(day: Date): Any? {
    var month = day.month
    var monthName = ""

    if (month == 0) {
        monthName = "Januar"
    }
    if (month == 1) {
        monthName = "Februar"
    }
    if (month == 2) {
        monthName = "März"
    }
    if (month == 3) {
        monthName = "April"
    }
    if (month == 4) {
        monthName = "Mai"
    }
    if (month == 5) {
        monthName = "Juni"
    }
    if (month == 6) {
        monthName = "Juli"
    }
    if (month == 7) {
        monthName = "August"
    }
    if (month == 8) {
        monthName = "September"
    }
    if (month == 9) {
        monthName = "Oktober"
    }
    if (month == 10) {
        monthName = "November"
    }
    if (month == 11) {
        monthName = "Dezemeber"
    }

    return monthName
}

fun dayOfWeekString(day: Date): Any {
    var dayOfWeek = day.day
    var dayOfWeekString = ""

    if (dayOfWeek == 0) {
        dayOfWeekString = "Sonntag"
    }
    if (dayOfWeek == 1) {
        dayOfWeekString = "Montag"
    }
    if (dayOfWeek == 2) {
        dayOfWeekString = "Dienstag"
    }
    if (dayOfWeek == 3) {
        dayOfWeekString = "Mittwoch"
    }
    if (dayOfWeek == 4) {
        dayOfWeekString = "Donnerstag"
    }
    if (dayOfWeek == 5) {
        dayOfWeekString = "Freitag"
    }
    if (dayOfWeek == 6) {
        dayOfWeekString = "Samstag"
    }

    return dayOfWeekString
}
