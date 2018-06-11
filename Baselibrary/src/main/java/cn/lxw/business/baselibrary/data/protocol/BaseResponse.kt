package cn.lxw.business.baselibrary.data.protocol

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月11日 下午11:39
 * *******************************
 * 备注：基础相应类型
 * 功能描述：
 */
data class BaseResponse<T>(val status: Int, val message: String, val data: T)