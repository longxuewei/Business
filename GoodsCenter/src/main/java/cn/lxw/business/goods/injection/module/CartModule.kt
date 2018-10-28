package cn.lxw.business.goods.injection.module

import cn.lxw.business.goods.service.CartService
import cn.lxw.business.goods.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/*
    购物车Module
 */
@Module
class CartModule {

    @Provides
    fun provideCartservice(cartService: CartServiceImpl): CartService {
        return cartService
    }

}
