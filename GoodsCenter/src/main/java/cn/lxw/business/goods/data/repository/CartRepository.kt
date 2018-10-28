package cn.lxw.business.goods.data.repository

import cn.lxw.business.baselibrary.data.net.RetrofitFactory
import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.goods.data.api.CartApi
import cn.lxw.business.goods.data.protocol.AddCartReq
import cn.lxw.business.goods.data.protocol.CartGoods
import cn.lxw.business.goods.data.protocol.DeleteCartReq
import cn.lxw.business.goods.data.protocol.SubmitCartReq
import io.reactivex.Observable

import javax.inject.Inject

/*
    购物车数据层
 */
class CartRepository @Inject constructor() {

    /*
        获取购物车列表
     */
    fun getCartList(): Observable<BaseResponse<MutableList<CartGoods>?>> {
        return RetrofitFactory.instance.create(CartApi::class.java).getCartList()
    }

    /*
        添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String): Observable<BaseResponse<Int>> {
        return RetrofitFactory.instance.create(CartApi::class.java)
                .addCart(AddCartReq(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku))
    }

    /*
        删除购物车商品
     */
    fun deleteCartList(list: List<Int>): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(CartApi::class.java).deleteCartList(DeleteCartReq(list))
    }

    /*
        购物车结算
     */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<BaseResponse<Int>> {
        return RetrofitFactory.instance.create(CartApi::class.java).submitCart(SubmitCartReq(list, totalPrice))
    }


}
