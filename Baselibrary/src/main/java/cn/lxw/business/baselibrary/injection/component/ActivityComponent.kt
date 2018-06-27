package cn.lxw.business.baselibrary.injection.component

import android.app.Activity
import cn.lxw.business.baselibrary.injection.ActivityScope
import cn.lxw.business.baselibrary.injection.module.ActivityModule
import cn.lxw.business.baselibrary.injection.module.LifeCycleProviderModule
import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Component

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月25日 下午9:03
 * *******************************
 * 备注：
 * 功能描述：
 */
@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(ActivityModule::class), (LifeCycleProviderModule::class)])
interface ActivityComponent {
    fun activity(): Activity
    fun lifeCycleProviderModule(): LifecycleProvider<*>
}