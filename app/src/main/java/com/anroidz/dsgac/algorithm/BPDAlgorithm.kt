package com.anroidz.dsgac.algorithm

import com.anroidz.dsgac.algorithm.datasource.CsvData
import com.anroidz.dsgac.algorithm.model.GestationalWeeks

/**
 * 作用描述:
 * 组件描述:
 * 创建人 rentl
 * 创建日期 2023/6/29
 * 修改日期 2023/6/29
 * 版权 pub
 */
class BPDAlgorithm : ReferenceTableAlgorithm() {
    override fun calCurGestationalWeek(): GestationalWeeks {
        if (lenSize == -1) {
            throw RuntimeException("BPD长度必填")
        }
        val data = CsvData.bpdData[lenSize] ?: throw RuntimeException("BPD长度不合法")

        return data
    }
}