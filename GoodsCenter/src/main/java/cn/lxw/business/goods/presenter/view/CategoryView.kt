package cn.lxw.business.goods.presenter.view

import cn.lxw.business.baselibrary.presenter.view.BaseView
import cn.lxw.business.goods.data.protocol.Category

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年8月14日21:26:24
 * *******************************
 * 备注：
 * 功能描述：
 */
interface CategoryView : BaseView {
    fun onGetCategoryResult(result: MutableList<Category>?)
}