package cn.lxw.business.goods.service.impl

import cn.lxw.business.baselibrary.ext.convert
import cn.lxw.business.goods.data.protocol.Category
import cn.lxw.business.goods.data.repository.CategoryRepository
import cn.lxw.business.goods.service.CategoryService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午11:14
 * *******************************
 * 备注：代码编译不报错了， 准备开始理一理Rx相关的东西。几个关键字：Subscript,Observable,Observer
 * 功能描述：
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {


    @Inject
    lateinit var categoryRepository: CategoryRepository


    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
        return categoryRepository.getCategory(parentId).convert()
    }

}