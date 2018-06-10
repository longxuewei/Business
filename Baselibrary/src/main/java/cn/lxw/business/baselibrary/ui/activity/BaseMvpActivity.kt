package cn.lxw.business.baselibrary.ui.activity

import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.baselibrary.presenter.view.BaseView

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午10:05
 * *******************************
 * 备注：
 * 功能描述：
 */
open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(),BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    lateinit var presenter: T
}