package cn.lxw.business.goods.service

import cn.lxw.business.goods.data.protocol.Goods
import io.reactivex.Observable

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月16日 21:36
 * *******************************
 * 备注：
 * 功能描述：
 */
interface GoodsService {

    /**
     * 获取商品列表
     * [categoryId]:分类ID
     * [pageNo]: 页码
     */
    fun getGoodsList(categoryId: Int, pageNo: Int): Observable<MutableList<Goods>?>


    /**
     * 根据关键字查询商品
     * [keyword]: 关键字
     * [pageNo]: 页码
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int): Observable<MutableList<Goods>?>


    /**
     * 获取商品详情
     */
    fun getGoodsDetail(goodsId: Int): Observable<Goods>


}