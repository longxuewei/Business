package cn.lxw.business.goods.data.repository

import cn.lxw.business.baselibrary.data.net.RetrofitFactory
import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.goods.data.api.GoodsApi
import cn.lxw.business.goods.data.protocol.GetGoodsListReq
import com.kotlin.goods.data.protocol.Goods
import io.reactivex.Observable
import javax.inject.Inject

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月16日 21:38
 * *******************************
 * 备注：
 * 功能描述：
 */
class GoodsRepository @Inject constructor() {
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<BaseResponse<MutableList<Goods>?>> {
        return RetrofitFactory.instance.create(GoodsApi::class.java).getGoodsList(GetGoodsListReq(categoryId, pageNo))
    }
}