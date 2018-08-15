package cn.lxw.business.goods.service

import cn.lxw.business.goods.data.protocol.Category
import io.reactivex.Observable


/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午11:13
 * *******************************
 * 备注： 面向接口编程，就单独用户模块这一块，接口可以抽取，而实现交由具体类实现，虽然还只有一个实现类，但需要考虑将来的变化
 * 功能描述：
 */
interface CategoryService {
    fun getCategory(parentId: Int): Observable<MutableList<Category>?>
}