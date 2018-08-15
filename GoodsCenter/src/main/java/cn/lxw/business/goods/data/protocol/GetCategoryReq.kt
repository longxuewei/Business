package cn.lxw.business.goods.data.protocol

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月14日 20:57
 * *******************************
 * 备注：请求分类的参数.
 * 功能描述：[parentId]: 父级ID,当请求一级分类的时候, 该值为0 ,请求二级分类的时候,该值为一级分类的ID
 */

data class GetCategoryReq(val parentId: Int)