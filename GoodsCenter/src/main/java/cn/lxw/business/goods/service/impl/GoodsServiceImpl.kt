package cn.lxw.business.goods.service.impl

import cn.lxw.business.baselibrary.ext.convert
import cn.lxw.business.goods.data.repository.GoodsRepository
import cn.lxw.business.goods.service.GoodsService
import com.kotlin.goods.data.protocol.Goods
import io.reactivex.Observable
import javax.inject.Inject

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年8月16日21:37:49
 * *******************************
 * 备注：
 * 功能描述：
 */
class GoodsServiceImpl @Inject constructor() : GoodsService {


    @Inject
    lateinit var goodsRepository: GoodsRepository

    override fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?> {
        return goodsRepository.getGoodsList(categoryId, pageNo).convert()
    }


}