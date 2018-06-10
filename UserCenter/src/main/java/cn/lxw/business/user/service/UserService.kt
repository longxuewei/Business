package cn.lxw.business.user.service

import io.reactivex.Observable

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午11:13
 * *******************************
 * 备注：
 * 功能描述：
 */
interface UserService{
    fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean>
}