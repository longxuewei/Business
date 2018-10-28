package cn.lxw.business.goods.presenter.view

import cn.lxw.business.baselibrary.presenter.view.BaseView
import cn.lxw.business.goods.data.protocol.Goods

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月19日 22:25
 * *******************************
 * 备注：
 * 功能描述：
 */
interface GoodsDetailView : BaseView {
    /**
     * 商品详情
     */
    fun onGetGoodsDetailResult(result: Goods)
}