package cn.lxw.business.baselibrary.presenter.view

import cn.lxw.business.baselibrary.presenter.BasePresenter

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午10:01
 * *******************************
 * 备注：
 * 功能描述：
 */
interface BaseView{
    fun showLoading()
    fun hideLoading()
    fun onError()
}