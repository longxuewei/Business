package cn.lxw.business.goods.data.repository

import cn.lxw.business.baselibrary.data.net.RetrofitFactory
import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.goods.data.api.CategoryApi
import cn.lxw.business.goods.data.protocol.Category
import cn.lxw.business.goods.data.protocol.GetCategoryReq
import io.reactivex.Observable
import javax.inject.Inject

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月11日 下午11:49
 * *******************************
 * 备注： 不是很明白为什么要封装这一层
 * 功能描述：
 */
class CategoryRepository @Inject constructor() {

    fun getCategory(parentId: Int): Observable<BaseResponse<MutableList<Category>?>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
    }


}