package cn.lxw.business.goods.injection.component

import cn.lxw.business.baselibrary.injection.PerComponentScope
import cn.lxw.business.baselibrary.injection.component.ActivityComponent
import cn.lxw.business.goods.injection.module.CartModule
import dagger.Component

/*
    购物车Component
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(CartModule::class)])
interface CartComponent {
//    fun inject(fragment: CartFragment)
}
