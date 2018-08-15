package cn.lxw.business.goods.injection.module

import cn.lxw.business.goods.service.CategoryService
import cn.lxw.business.goods.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年8月14日21:34:433
 * *******************************
 * 备注：
 * 功能描述：
 */
@Module
class CategoryModule {

    @Provides
    fun providesCategoryService(categoryService: CategoryServiceImpl): CategoryService {
        return categoryService
    }
}