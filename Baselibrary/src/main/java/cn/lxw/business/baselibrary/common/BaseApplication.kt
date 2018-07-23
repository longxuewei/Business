package cn.lxw.business.baselibrary.common

import android.app.Application
import android.content.Context
import cn.lxw.business.baselibrary.injection.component.AppComponent
import cn.lxw.business.baselibrary.injection.component.DaggerAppComponent
import cn.lxw.business.baselibrary.injection.module.AppModule

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月25日 下午9:04
 * *******************************
 * 备注：
 * 功能描述：
 */
class BaseApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppInjection()
        context = this
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


    companion object {
        lateinit var context: Context
    }
}