package cn.lxw.business.goods.injection.module

import cn.lxw.business.goods.service.GoodsService
import cn.lxw.business.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年8月16日21:39:16
 * *******************************
 * 备注：
 * 功能描述：
 */
@Module
class GoodsModule {

    @Provides
    fun providesGoodsService(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }
}