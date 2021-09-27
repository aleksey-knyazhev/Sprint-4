package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*
import kotlin.collections.ArrayList

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val result = mutableSetOf<String>()

    for (i in ZoneId.getAvailableZoneIds())
        if (TimeZone.getTimeZone(i).rawOffset % 3600000 != 0)
            result.add(i)

    return result
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val result = ArrayList<String>()

    for (i in 1..12)
        result.add(LocalDate.of(year, Month.of(i), 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString())

    return result
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var result = 0

    for (i in 1..12)
        result += (LocalDate.of(year, Month.of(i), 13).dayOfWeek == DayOfWeek.FRIDAY).toInt()

    return result
}

fun Boolean.toInt() = if (this) 1 else 0

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    //                                        "01 Aug 2021, 23:39"
    return DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.ENGLISH).format(dateTime)
}



