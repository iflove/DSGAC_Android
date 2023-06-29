package com.anroidz.dsgac.algorithm.datasource

import com.anroidz.dsgac.algorithm.model.GestationalWeeks

/**
 * 作用描述: CsvData
 * 组件描述: #基础组件 #组件名 （子组件）
 * 组件版本: v1
 * 创建人 rentl
 * 创建日期 2023/6/29 08:17
 * 修改日期 2023/6/29 08:17
 * 版权 pub
 */

object CsvData {

    //唐氏筛查孕周与双顶径（BPD）对应值参考表,,
    //BPD长度（mm）,孕周（周）,孕周（天）
    private val bpdCsvData = """
    22,11,5
    23,12,1
    24,12,3
    25,12,6
    26,13,1
    27,13,4
    28,13,6
    29,14,2
    30,14,4
    31,15,0
    32,15,2
    33,15,5
    34,16,0
    35,16,3
    36,16,5
    37,17,1
    38,17,3
    39,17,6
    40,18,1
    41,18,4
    42,18,6
    43,19,2
    44,19,4
    45,19,6
    46,20,2
    47,20,4
    48,21,0
    49,21,2
    50,21,5
    51,22,0
    52,22,3
    53,22,5
    54,23,1
    55,23,3
    56,23,6
    57,24,1
    58,24,4
    59,24,6
    60,25,1
    61,25,4
    62,25,6
""".trimIndent()

    //唐氏筛查孕周与头臀长（CRL）对应值参考表,,
    //CRL长度（mm）,孕周（周）,孕周（天）
    private val crlCsvData = """
    20,8,4
    21,8,5
    22,8,6
    23,9,0
    24,9,1
    25,9,2
    26,9,2
    27,9,3
    28,9,4
    29,9,5
    30,9,6
    31,9,6
    32,10,0
    33,10,1
    34,10,2
    35,10,2
    36,10,3
    37,10,4
    38,10,5
    39,10,5
    40,10,6
    41,11,0
    42,11,0
    43,11,1
    44,11,1
    45,11,2
    46,11,3
    47,11,3
    48,11,4
    49,11,4
    50,11,5
    51,11,6
    52,11,6
    53,12,0
    54,12,0
    55,12,1
    56,12,1
    57,12,2
    58,12,2
    59,12,3
    60,12,4
    61,12,4
    62,12,5
    63,12,5
    64,12,6
    65,12,6
    66,13,0
    67,13,0
    68,13,1
    69,13,1
    70,13,2
    71,13,2
    72,13,3
    73,13,3
    74,13,4
    75,13,4
    76,13,5
    77,13,5
    78,13,6
    79,13,6
""".trimIndent()

    val bpdData by lazy { getBpdData() }


    private fun getBpdData(): MutableMap<Int, GestationalWeeks> {
        val map = linkedMapOf<Int, GestationalWeeks>()
        for (line in bpdCsvData.split("\n")) {
            val item = line.split(",")
            map[item[0].toInt()] = GestationalWeeks(item[1].toInt(), item[2].toInt())
        }
        return map
    }


    val crlData by lazy { getCRLData() }


    private fun getCRLData(): MutableMap<Int, GestationalWeeks> {
        val map = linkedMapOf<Int, GestationalWeeks>()
        for (line in crlCsvData.split("\n")) {
            val item = line.split(",")
            map[item[0].toInt()] = GestationalWeeks(item[1].toInt(), item[2].toInt())
        }
        return map
    }
}
