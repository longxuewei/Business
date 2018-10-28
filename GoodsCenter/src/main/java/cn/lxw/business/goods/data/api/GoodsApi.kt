package cn.lxw.business.goods.data.api

import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.goods.data.protocol.GetGoodsDetailReq
import cn.lxw.business.goods.data.protocol.GetGoodsListByKeywordReq
import cn.lxw.business.goods.data.protocol.GetGoodsListReq
import cn.lxw.business.goods.data.protocol.Goods
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/*
    商品接口
 */
interface GoodsApi {
    /*
        获取商品列表
     */
    @POST("goods/getGoodsList")
    fun getGoodsList(@Body req: GetGoodsListReq): Observable<BaseResponse<MutableList<Goods>?>>

    /*
        获取商品列表
     */
    @POST("goods/getGoodsListByKeyword")
    fun getGoodsListByKeyword(@Body req: GetGoodsListByKeywordReq): Observable<BaseResponse<MutableList<Goods>?>>

    /*
        获取商品详情
     */
    @POST("goods/getGoodsDetail")
    fun getGoodsDetail(@Body req: GetGoodsDetailReq): Observable<BaseResponse<Goods>>
}
