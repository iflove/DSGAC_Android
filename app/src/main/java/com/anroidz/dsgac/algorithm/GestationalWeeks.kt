package com.anroidz.dsgac.algorithm

/**
 * 作用描述: 孕周
 * 组件描述: #基础组件 #组件名 （子组件）
 * 组件版本: v1
 * 创建人 rentl
 * 创建日期 2023/6/29 08:10
 * 修改日期 2023/6/29 08:10
 * 版权 pub
 */
data class GestationalWeeks(
    //孕周（周）, 7天==1周
    val week: Int,
    //孕周（天）0-6 , 满6进1
    val day: Int,
)
