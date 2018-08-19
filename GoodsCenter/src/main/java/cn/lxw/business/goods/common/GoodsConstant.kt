package cn.lxw.business.goods.common

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月11日 下午9:52
 * *******************************
 * 备注：基础服务器地址
 * 功能描述：
 */
class GoodsConstant {
    companion object {
        /** 商品分类ID  */
        const val KEY_GOODS_CATEGORY_ID = "categoryId"
        /** 搜索历史 本地存储 */
        const val SP_SEARCH_HISTORY = "search_history"
        /** 搜索商品类型 */
        const val KEY_SEARCH_GOODS_TYPE = "search_goods_type"
        /** 按关键字搜索 */
        const val SEARCH_GOODS_TYPE_KEYWORD = 1
        /** 商品关键字 */
        const val KEY_GOODS_KEYWORD = "goods_keyword"
        /** 商品ID */
        const val KEY_GOODS_ID = "goods_id"
    }
}