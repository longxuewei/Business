package cn.lxw.business.goods.data.api

import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.goods.data.protocol.Category
import cn.lxw.business.goods.data.protocol.GetCategoryReq
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月14日 20:55
 * *******************************
 * 备注：分类获取分类
 * 功能描述：
 */
interface CategoryApi {


    /**
     * 获取分类列表:
     * [req]: 请求参数[cn.lxw.business.goods.data.protocol.GetCategoryReq]
     */
    @POST("category/getCategory")
    fun getCategory(@Body req: GetCategoryReq): Observable<BaseResponse<MutableList<Category>?>>
}