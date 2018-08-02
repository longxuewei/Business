package cn.lxw.business.user.data.protocol

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月11日 下午11:42
 * *******************************
 * 备注：注册的请求实体，用于封装注册的参数
 * 功能描述：
 */
data class RegisterReq(val mobile: String, val pwd: String, val verifyCode: String)