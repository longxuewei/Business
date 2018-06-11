package cn.lxw.business.user.service.impl

import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.user.data.repository.UserRepository
import cn.lxw.business.user.service.UserService
import io.reactivex.Observable
import rx.functions.Func1

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午11:14
 * *******************************
 * 备注：
 * 功能描述：
 */
class UserServiceImpl : UserService {
    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        val repository = UserRepository()
        repository.register(mobile, pwd, verifyCode).flatMap(object : Func1<BaseResponse<String>, Observable<Boolean>> {})
    }
}