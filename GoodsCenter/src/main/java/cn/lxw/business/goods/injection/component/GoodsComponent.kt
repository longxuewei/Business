package cn.lxw.business.goods.injection.component

import cn.lxw.business.baselibrary.injection.PerComponentScope
import cn.lxw.business.baselibrary.injection.component.ActivityComponent
import cn.lxw.business.goods.injection.module.GoodsModule
import cn.lxw.business.goods.ui.activity.GoodsListActivity
import cn.lxw.business.goods.ui.fragment.GoodsDetailTabOneFragment
import dagger.Component

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月16日 21:41
 * *******************************
 * 备注：
 * 功能描述：
 */
@PerComponentScope
@Component(modules = [(GoodsModule::class)], dependencies = [(ActivityComponent::class)])
interface GoodsComponent {
    fun inject(goodsListActivity: GoodsListActivity)
    fun inject(goodsDetailTabOneFragment: GoodsDetailTabOneFragment)
}