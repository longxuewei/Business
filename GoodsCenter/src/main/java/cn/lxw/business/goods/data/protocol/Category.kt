package cn.lxw.business.goods.data.protocol

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月14日 20:57
 * *******************************
 * 备注：商品分类
 * 功能描述：
 */
data class Category(
        /** 分类ID */
        val id: Int,
        /** 分类名称 */
        val categoryName: String,
        /** 分类图标 */
        val categoryIcon: String = "",
        /** 分类父级ID */
        val parentId: Int,
        /** 是否被选中 */
        var isSelected: Boolean = false
)
