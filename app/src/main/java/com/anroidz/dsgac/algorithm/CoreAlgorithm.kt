package com.anroidz.dsgac.algorithm

import com.anroidz.dsgac.algorithm.model.GestationalWeeks
import java.util.Date

/**
 * 作用描述:
 * 组件描述:
 * 创建人 rentl
 * 创建日期 2023/6/29
 * 修改日期 2023/6/29
 * 版权 zhd
 */
abstract class CoreAlgorithm : IAlgorithm {
    private lateinit var inspectDate: Date

    override fun setInspectDate(date: Date) {
        this.inspectDate = date
    }

    override fun getInspectDate(): Date {
        return inspectDate
    }

}