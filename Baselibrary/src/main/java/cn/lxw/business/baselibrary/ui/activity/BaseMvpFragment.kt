package cn.lxw.business.baselibrary.ui.activity

import android.os.Bundle
import cn.lxw.business.baselibrary.common.BaseApplication
import cn.lxw.business.baselibrary.injection.component.ActivityComponent
import cn.lxw.business.baselibrary.injection.component.DaggerActivityComponent
import cn.lxw.business.baselibrary.injection.module.ActivityModule
import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午10:05
 * *******************************
 * 备注：Activity的基类，基于MVP设计模式。持有业务逻辑层（使用依赖注入）
 * 功能描述：
 */
open class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    /** 逻辑层 */
    @Inject
    lateinit var presenter: T

    lateinit var activityComponent: ActivityComponent


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
    }

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder().appComponent((activity.application as BaseApplication).appComponent).activityModule(ActivityModule(activity)).build()
    }
}