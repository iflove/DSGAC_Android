package com.anroidz.dsgac.algorithm

import com.anroidz.dsgac.algorithm.model.GestationalWeeks
import com.anroidz.dsgac.algorithm.utils.TimeUtils

/**
 * 作用描述: 辅助算法
 * 组件描述:
 * 创建人 rentl
 * 创建日期 2023/6/29
 * 修改日期 2023/6/29
 * 版权 pub
 */
class AuxiliaryAlgorithm : CoreAlgorithm() {
    override fun calCurGestationalWeek(): GestationalWeeks {
        val inspectDate = getInspectDate()
        val curStandardDate = getCurStandardDate()

        return TimeUtils.calCurGestationalWeek(GestationalWeeks(0, 0), curStandardDate, inspectDate)
    }
}