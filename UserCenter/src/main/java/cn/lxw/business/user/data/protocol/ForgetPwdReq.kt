package cn.lxw.business.user.data.protocol

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月11日 下午11:42
 * *******************************
 * 备注：忘记密码请求实体
 * 功能描述：
 */
data class ForgetPwdReq(val mobile: String, val verifyCode: String)