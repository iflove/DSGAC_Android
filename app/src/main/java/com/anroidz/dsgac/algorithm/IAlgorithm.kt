package com.anroidz.dsgac.algorithm

import com.anroidz.dsgac.algorithm.model.GestationalWeeks
import java.util.Date

/**
 * 作用描述: 算法接口
 * 组件描述:
 * 创建人 rentl
 * 创建日期 2023/6/29
 * 修改日期 2023/6/29
 * 版权 pub
 */
interface IAlgorithm {

    /**
     * 早期的孕周开始
     */
    val earlyGestationalWeeksStart: GestationalWeeks
        get() = GestationalWeeks(10, 0)

    /**
     * 早期的孕周结束
     */
    val earlyGestationalWeeksEnd: GestationalWeeks
        get() = GestationalWeeks(13, 6)

    /**
     * 中期的孕周开始
     */
    val midtermGestationalWeeksStart: GestationalWeeks
        get() = GestationalWeeks(15, 0)

    /**
     * 中期的孕周结束
     */
    val midtermGestationalWeeksEnd: GestationalWeeks
        get() = GestationalWeeks(20, 6)

    /**
     * 获取当前标准日期
     */
    fun getCurStandardDate(): Date {
        return Date()
    }

    /**
     * set检查日期
     */
    fun setInspectDate(date: Date)

    /**
     *
     * 获取检查日期
     */
    fun getInspectDate(): Date

    /**
     * 计算当前孕周
     */
    fun calCurGestationalWeek(): GestationalWeeks
}