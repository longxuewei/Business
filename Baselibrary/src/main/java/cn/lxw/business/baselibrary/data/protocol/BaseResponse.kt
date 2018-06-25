package cn.lxw.business.baselibrary.data.protocol

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月11日 下午11:39
 * *******************************
 * 备注：基础相应类型
 * 1. 大多数服务器返回的数据，在最外层都包装了一层，用于说明此次请求的结果信息的字段：
 * 2. 本类字段说明：
 * [status]：状态码
 * [message]：说明信息
 * [data]：真正需要的数据
 *
 *
 * 功能描述： 格式如下：
 *
 *
 * [{"data":{"ahahah":"hahahah","heihei":"heihei"},"status":1,"message":"这是提示信息"}]
 */
data class BaseResponse<T>(val status: Int, val message: String, val data: T)