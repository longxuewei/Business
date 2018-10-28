package cn.lxw.business.goods.service.impl

import cn.lxw.business.baselibrary.ext.convert
import cn.lxw.business.baselibrary.ext.convertBoolean
import cn.lxw.business.goods.data.protocol.CartGoods
import cn.lxw.business.goods.data.repository.CartRepository
import cn.lxw.business.goods.service.CartService
import io.reactivex.Observable
import javax.inject.Inject

/*
    购物车 业务层实现类
 */
class CartServiceImpl @Inject constructor() : CartService {

    @Inject
    lateinit var repository: CartRepository

    /*
        加入购物车
     */
    override fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long, goodsCount: Int, goodsSku: String): Observable<Int> {
        return repository.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice,
                goodsCount, goodsSku).convert()
    }

    /*
        获取购物车列表
     */
    override fun getCartList(): Observable<MutableList<CartGoods>?> {
        return repository.getCartList().convert()
    }

    /*
        删除购物车商品
     */
    override fun deleteCartList(list: List<Int>): Observable<Boolean> {
        return repository.deleteCartList(list).convertBoolean()
    }

    /*
        提交购物车商品
     */
    override fun submitCart(list: MutableList<CartGoods>, totalPrice: Long): Observable<Int> {
        return repository.submitCart(list, totalPrice).convert()
    }
}
