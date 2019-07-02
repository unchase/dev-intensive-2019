package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern : String = "HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time
    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time

    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    if (date == null)
        return "Еще ни разу не был"

    var diff = date.time - this.time

    if (diff >= 0)
    {
        when(diff / SECOND)
        {
            in 0..1 -> return "только что"
            in 1..45 -> return "несколько секунд назад"
            in 45..75 -> return "минуту назад"
        }

        if (diff / SECOND >= 75 && diff / MINUTE <= 45)
        {
            val minutes = diff / MINUTE
            return when(minutes.toString()[minutes.toString().length - 1])
            {
                '1' ->
                    if (minutes.toString().length > 1 && minutes.toString()[minutes.toString().length - 2] == '1')
                        "$minutes минут назад"
                    else
                        "$minutes минуту назад"
                '2', '3', '4' ->
                    if (minutes.toString().length > 1 && minutes.toString()[minutes.toString().length - 2] == '1')
                        "$minutes минут назад"
                    else
                        "$minutes минуты назад"
                else -> "$minutes минут назад"
            }
        }

        if (diff / MINUTE in 45..75)
            return "час назад"

        if (diff / MINUTE >= 75 && diff / HOUR <= 22)
        {
            val hours = diff / HOUR
            return when(hours.toString()[hours.toString().length - 1])
            {
                '1' ->
                    if (hours.toString().length > 1 && hours.toString()[hours.toString().length - 2] == '1')
                        "$hours часов назад"
                    else
                        "$hours час назад"
                '2', '3', '4' ->
                    if (hours.toString().length > 1 && hours.toString()[hours.toString().length - 2] == '1')
                        "$hours часов назад"
                    else
                        "$hours часа назад"
                else -> "$hours часов назад"
            }
        }

        if (diff / HOUR in 22..26)
            return "день назад"

        if (diff / HOUR >= 26 && diff / DAY <= 360)
        {
            val days = diff / DAY
            return when(days.toString()[days.toString().length - 1])
            {
                '1' ->
                    if (days.toString().length > 1 && days.toString()[days.toString().length - 2] == '1')
                        "$days дней назад"
                    else
                        "$days день назад"
                '2', '3', '4' ->
                    if (days.toString().length > 1 && days.toString()[days.toString().length - 2] == '1')
                        "$days дней назад"
                    else
                        "$days дня назад"
                else -> "$days дней назад"
            }
        }

        if (diff / DAY > 360)
            return "более года назад"
    }
    else
    {
        diff = -diff

        when (diff / SECOND)
        {
            in 0..1 -> return "только что"
            in 1..45 -> return "через несколько секунд"
            in 45..75 -> return "через минуту"
        }

        if (diff / SECOND >= 75 && diff / MINUTE <= 45)
        {
            val minutes = diff / MINUTE
            return when(minutes.toString()[minutes.toString().length - 1])
            {
                '1' ->
                    if (minutes.toString().length > 1 && minutes.toString()[minutes.toString().length - 2] == '1')
                        "через $minutes минут"
                    else
                        "через $minutes минуту"
                '2', '3', '4' ->
                    if (minutes.toString().length > 1 && minutes.toString()[minutes.toString().length - 2] == '1')
                        "через $minutes минут"
                    else
                        "через $minutes минуты"
                else -> "через $minutes минут"
            }
        }

        if (diff / MINUTE in 45..75)
            return "через час"

        if (diff / MINUTE >= 75 && diff / HOUR <= 22)
        {
            val hours = diff / HOUR
            return when(hours.toString()[hours.toString().length - 1])
            {
                '1' ->
                    if (hours.toString().length > 1 && hours.toString()[hours.toString().length - 2] == '1')
                        "через $hours часов"
                    else
                        "через $hours час"
                '2', '3', '4' ->
                    if (hours.toString().length > 1 && hours.toString()[hours.toString().length - 2] == '1')
                        "через $hours часов"
                    else
                        "через $hours часа"
                else -> "через $hours часов"
            }
        }

        if (diff / HOUR in 22..26)
            return "через день"

        if (diff / HOUR >= 26 && diff / DAY <= 360)
        {
            val days = diff / DAY
            return when(days.toString()[days.toString().length - 1])
            {
                '1' ->
                    if (days.toString().length > 1 && days.toString()[days.toString().length - 2] == '1')
                        "через $days дней"
                    else
                        "через $days день"
                '2', '3', '4' ->
                    if (days.toString().length > 1 && days.toString()[days.toString().length - 2] == '1')
                        "через $days дней"
                    else
                        "через $days дня"
                else -> "через $days дней"
            }
        }

        if (diff / DAY > 360)
            return "более чем через год"
    }

    return "не известно когда"
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}