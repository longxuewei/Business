package cn.lxw.business.user.service.impl

import cn.lxw.business.baselibrary.ext.convert
import cn.lxw.business.baselibrary.ext.convertBoolean
import cn.lxw.business.user.data.protocol.UserInfo
import cn.lxw.business.user.data.repository.UserRepository
import cn.lxw.business.user.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午11:14
 * *******************************
 * 备注：代码编译不报错了， 准备开始理一理Rx相关的东西。几个关键字：Subscript,Observable,Observer
 * 功能描述：
 */
class UserServiceImpl @Inject constructor() : UserService {


    @Inject
    lateinit var repository: UserRepository


    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile, pwd, pushId).convert()
    }


    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile, pwd, verifyCode).convertBoolean()
    }


    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return repository.forget(mobile, verifyCode).convertBoolean()
    }


    override fun resetPwd(mobile:String ,pwd: String, confirmPwd: String): Observable<Boolean> {
        return repository.resetPwd(mobile,pwd, confirmPwd).convertBoolean()
    }


}