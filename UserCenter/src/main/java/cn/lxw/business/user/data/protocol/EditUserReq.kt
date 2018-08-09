package cn.lxw.business.user.data.protocol
/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018-8-8 21:31:54
 * *******************************
 * 备注： 修改用户信息的请求体
 * 功能描述：
 */
data class EditUserReq(val userIcon: String, val userName: String, val gender: String, val sign: String)
