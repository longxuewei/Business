package cn.lxw.business.baselibrary.common

import android.app.Application
import android.content.Context
import cn.lxw.business.baselibrary.BuildConfig
import cn.lxw.business.baselibrary.injection.component.AppComponent
import cn.lxw.business.baselibrary.injection.component.DaggerAppComponent
import cn.lxw.business.baselibrary.injection.module.AppModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

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
        context = this
        initAppInjection()
        initLogger()

    }


    /**
     * 初始化注入器
     */
    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    /**
     * 初始化日志打印工具
     */
    private fun initLogger() {
        val loggerConfiguration = PrettyFormatStrategy.newBuilder()
                .tag("Business")
                .methodCount(0)
                .methodOffset(0)
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(loggerConfiguration) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }


    companion object {
        lateinit var context: Context
    }
}