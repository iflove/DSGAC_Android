package com.anroidz.dsgac.algorithm.utils

import com.anroidz.dsgac.algorithm.model.GestationalWeeks
import java.text.SimpleDateFormat
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

    fun calculateGestationalWeekDate(gestationalWeek: GestationalWeeks, curStandardDate: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = curStandardDate
        calendar.add(Calendar.WEEK_OF_YEAR, gestationalWeek.week)
        calendar.add(Calendar.DAY_OF_YEAR, gestationalWeek.day)
        return calendar.time
    }

    fun createDate(year: Int, monthOfYear: Int, dayOfMonth: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, monthOfYear - 1, dayOfMonth) // 注意月份是从 0 开始的，所以需要减去 1
        return calendar.time
    }

    fun getDiffDays(maxGestationalWeek: GestationalWeeks, calCurGestationalWeek: GestationalWeeks): Int {
        val days1 = maxGestationalWeek.week * 7 + (maxGestationalWeek.day + 1) // 将孕周转换为总天数
        val days2 = calCurGestationalWeek.week * 7 + (calCurGestationalWeek.day + 1)// 将孕周转换为总天数
        return days1 - days2
    }
    fun calculateWeeksDifference(weeks1: GestationalWeeks, weeks2: GestationalWeeks): GestationalWeeks {
        val days1 = weeks1.week * 7 + weeks1.day // 将孕周转换为总天数
        val days2 = weeks2.week * 7 + weeks2.day // 将孕周转换为总天数

        val totalDaysDifference = days1 - days2 // 计算总天数差异

        val newWeeks = totalDaysDifference / 7 // 计算新的孕周（周）
        val newDays = totalDaysDifference % 7 // 计算新的孕周（天）

        return GestationalWeeks(newWeeks, newDays) // 返回新的 GestationalWeeks 对象
    }

    fun formatDate(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }

}