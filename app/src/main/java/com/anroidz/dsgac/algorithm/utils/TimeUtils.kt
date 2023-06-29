package com.anroidz.dsgac.algorithm.utils

import com.anroidz.dsgac.algorithm.model.GestationalWeeks
import java.util.Calendar
import java.util.Date

/**
 * 作用描述:
 * 组件描述:
 * 创建人 rentl
 * 创建日期 2023/6/29
 * 修改日期 2023/6/29
 * 版权 zhd
 */
object TimeUtils {

    fun getDayDifference(curStandardDate: Date, inspectDate: Date): Long {
        val milliseconds1 = curStandardDate.time
        val milliseconds2 = inspectDate.time
        return (milliseconds1 - milliseconds2) / (24 * 60 * 60 * 1000)
    }

    fun addDaysToGestationalWeeks(gestationalWeeks: GestationalWeeks, days: Int): GestationalWeeks {
        val totalDays = gestationalWeeks.week * 7 + gestationalWeeks.day + days
        val newWeeks = totalDays / 7
        val newDays = totalDays % 7
        return GestationalWeeks(newWeeks, newDays)
    }

    fun calCurGestationalWeek(
        gestationalWeeks: GestationalWeeks,
        curStandardDate: Date,
        inspectDate: Date
    ): GestationalWeeks {
        return addDaysToGestationalWeeks(gestationalWeeks, getDayDifference(curStandardDate, inspectDate).toInt())
    }

    fun calculateGestationalWeekDate(calCurGestationalWeek: GestationalWeeks, curStandardDate: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = curStandardDate
        calendar.add(Calendar.WEEK_OF_YEAR, calCurGestationalWeek.week)
        calendar.add(Calendar.DAY_OF_YEAR, calCurGestationalWeek.day)
        return calendar.time
    }

    fun createDate(year: Int, monthOfYear: Int, dayOfMonth: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, monthOfYear - 1, dayOfMonth) // 注意月份是从 0 开始的，所以需要减去 1
        return calendar.time
    }

}