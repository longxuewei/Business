package cn.lxw.business.baselibrary.ui.activity

import android.os.Bundle
import cn.lxw.business.baselibrary.common.BaseApplication
import cn.lxw.business.baselibrary.injection.component.ActivityComponent
import cn.lxw.business.baselibrary.injection.component.DaggerActivityComponent
import cn.lxw.business.baselibrary.injection.module.ActivityModule
import cn.lxw.business.baselibrary.injection.module.LifeCycleProviderModule
import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.baselibrary.presenter.view.BaseView
import cn.lxw.business.baselibrary.widget.ProgressLoading
import org.jetbrains.anko.toast
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
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    /** 逻辑层 */
    @Inject
    lateinit var presenter: T

    lateinit var activityComponent: ActivityComponent


    /** 基础加载框 */
    lateinit var progressDialog: ProgressLoading


    override fun showLoading() {
        progressDialog.show()
    }

    override fun hideLoading() {
        progressDialog.hide()
    }

    override fun onError(msg: String) {
        toast(msg)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
        progressDialog = ProgressLoading.create(this)
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent).activityModule(ActivityModule(this))
                .lifeCycleProviderModule(LifeCycleProviderModule(this))
                .build()
    }
}