package cn.lxw.business.goods.data.api

import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.goods.data.protocol.AddCartReq
import cn.lxw.business.goods.data.protocol.CartGoods
import cn.lxw.business.goods.data.protocol.DeleteCartReq
import cn.lxw.business.goods.data.protocol.SubmitCartReq
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/*
    购物车 接口
 */
interface CartApi {
    /*
        获取购物车列表
     */
    @POST("cart/getList")
    fun getCartList(): Observable<BaseResponse<MutableList<CartGoods>?>>

    /*
        添加商品到购物车
     */
    @POST("cart/add")
    fun addCart(@Body req: AddCartReq): Observable<BaseResponse<Int>>

    /*
        删除购物车商品
     */
    @POST("cart/delete")
    fun deleteCartList(@Body req: DeleteCartReq): Observable<BaseResponse<String>>

    /*
        提交购物车商品
     */
    @POST("cart/submit")
    fun submitCart(@Body req: SubmitCartReq): Observable<BaseResponse<Int>>
}
