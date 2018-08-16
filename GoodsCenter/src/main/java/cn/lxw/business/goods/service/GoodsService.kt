package cn.lxw.business.goods.service

import com.kotlin.goods.data.protocol.Goods
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
    fun getGoodsList(categoryId: Int, pageNo: Int):Observable<MutableList<Goods>?>
}