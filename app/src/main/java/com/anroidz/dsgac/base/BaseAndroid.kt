package com.anroidz.dsgac.base

import android.view.View

/**
 * 作用描述: 组件描述
 * 组件描述: #基础组件 #组件名 （子组件）
 * 组件版本: v1
 * 创建人 rentl
 * 创建日期 2023/6/28 08:10
 * 修改日期 2023/6/28 08:10
 * 版权 pub
 */
//显示
inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }