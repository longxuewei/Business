package cn.lxw.business.user.data.repository

import cn.lxw.business.baselibrary.data.net.RetrofitFactory
import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.user.data.api.UserApi
import cn.lxw.business.user.data.protocol.RegisterReq
import io.reactivex.Observable
import javax.inject.Inject

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月11日 下午11:49
 * *******************************
 * 备注： 不是很明白为什么要封装这一层
 * 功能描述：
 */
class UserRepository @Inject constructor() {

    /**
     * 注册：此处直接和服务器进行通讯。
     * [mobile]：手机号码
     * [pwd]：密码
     * [verifyCode]：验证码
     *
     */
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).register(RegisterReq(mobile, pwd, verifyCode))
    }
}