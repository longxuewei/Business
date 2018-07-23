package cn.lxw.business.baselibrary.injection.module

import android.app.Activity
import cn.lxw.business.baselibrary.injection.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月25日 下午9:04
 * *******************************
 * 备注：提供全局的Context
 * 功能描述：
 */
@Module
class ActivityModule(private val activity: Activity) {

    /**
     * 提供全局的Context(Application)
     */
    @ActivityScope
    @Provides
    fun providesActivity(): Activity {
        return this.activity
    }
}