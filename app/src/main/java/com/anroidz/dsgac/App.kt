package com.anroidz.dsgac

import android.app.Application
import com.anroidz.dsgac.base.ResUtil

/**
 * 作用描述: 组件描述
 * 组件描述: #基础组件 #组件名 （子组件）
 * 组件版本: v1
 * 创建人 rentl
 * 创建日期 2023/6/24 10:45
 * 修改日期 2023/6/24 10:45
 * 版权 pub
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ResUtil.inject(this, R::class.java.`package`.name)
    }
}