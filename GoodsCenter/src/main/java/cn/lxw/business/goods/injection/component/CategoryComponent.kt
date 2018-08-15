package cn.lxw.business.goods.injection.component

import cn.lxw.business.baselibrary.injection.PerComponentScope
import cn.lxw.business.baselibrary.injection.component.ActivityComponent
import cn.lxw.business.goods.injection.module.CategoryModule
import cn.lxw.business.goods.ui.fragment.CategoryFragment
import dagger.Component

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月21日 下午10:14
 * *******************************
 * 备注：
 * 功能描述：
 */
@PerComponentScope
@Component(modules = [(CategoryModule::class)], dependencies = [ActivityComponent::class])
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)
}