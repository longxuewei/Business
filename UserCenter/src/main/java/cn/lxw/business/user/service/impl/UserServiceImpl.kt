package cn.lxw.business.user.service.impl

import android.util.Log
import cn.lxw.business.baselibrary.data.protocol.BaseResponse
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

    override fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean> {
        return repository.register(mobile, pwd, verifyCode).flatMap(object : Function1<BaseResponse<String>, Observable<Boolean>> {
            override fun invoke(p1: BaseResponse<String>): Observable<Boolean> {
                Log.d("TAG:", "哈哈成功啦")
                return Observable.just(true)
            }
        })
//        repository.register(mobile, pwd, verifyCode).subscribe(object : Consumer<BaseResponse<String>> {
//            override fun accept(t: BaseResponse<String>) {
//
//            }
//
//        })

    }
}