package cn.lxw.business.baselibrary.injection.module

import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月25日 下午9:04
 * *******************************
 * 备注：提供LifeCycleProvider 用于处理Rx引起的内存泄露问题。
 * 功能描述：
 */
@Module
class LifeCycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    /**
     * 提供LifeCycleProvider 用于处理Rx引起的内存泄露问题。
     */
    @Provides
    fun providesLifecycleProvider(): LifecycleProvider<*> = lifecycleProvider

}