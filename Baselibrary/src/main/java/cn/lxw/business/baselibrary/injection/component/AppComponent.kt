package cn.lxw.business.baselibrary.injection.component

import android.content.Context
import cn.lxw.business.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月25日 下午9:03
 * *******************************
 * 备注：
 * 功能描述：
 */
@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun context(): Context
}