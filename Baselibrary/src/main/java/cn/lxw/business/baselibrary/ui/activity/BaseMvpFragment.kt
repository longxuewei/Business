package cn.lxw.business.baselibrary.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.lxw.business.baselibrary.common.BaseApplication
import cn.lxw.business.baselibrary.injection.component.ActivityComponent
import cn.lxw.business.baselibrary.injection.component.DaggerActivityComponent
import cn.lxw.business.baselibrary.injection.module.LifeCycleProviderModule
import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.baselibrary.presenter.view.BaseView
import org.jetbrains.anko.support.v4.toast
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
abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    /** 逻辑层 */
    @Inject
    lateinit var presenter: T

    lateinit var activityComponent: ActivityComponent


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError(msg: String) {
        toast(msg)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        injectComponent()
        initActivityInjection()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun injectComponent()


    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent
                .builder()
                .appComponent((activity?.application as BaseApplication).appComponent)
                .lifeCycleProviderModule(LifeCycleProviderModule(this))
                .build()
    }
}