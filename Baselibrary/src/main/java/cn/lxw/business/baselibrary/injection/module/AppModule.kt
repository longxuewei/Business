package cn.lxw.business.baselibrary.injection.module

import android.content.Context
import cn.lxw.business.baselibrary.common.BaseApplication
import cn.lxw.business.baselibrary.ui.activity.BaseActivity
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
class AppModule(private val context: BaseApplication) {

    /**
     * 提供全局的Context(Application)
     */
    @Provides
    @Singleton
    fun providesContext(): Context {
        return this.context
    }
}