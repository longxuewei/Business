package cn.lxw.business.user.data.repository

import cn.lxw.business.baselibrary.data.net.RetrofitFactory
import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.user.data.api.UserApi
import cn.lxw.business.user.data.protocol.*
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


    /**
     * 登陆
     * [mobile]：手机号/账号
     * [pwd]：密码
     * [pushId]：推送ID
     */
    fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResponse<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java).login(LoginReq(mobile, pwd, pushId))
    }


    /**
     * 忘记密码：
     * [mobile]：手机号/账号
     * [verifyCode]：验证码
     */
    fun forget(mobile: String, verifyCode: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).forgetPwd(ForgetPwdReq(mobile, verifyCode))
    }

    /**
     * 重置密码：
     * [pwd]：新密码
     * [confirmPwd]：重复新密码
     */
    fun resetPwd(mobile: String, pwd: String, confirmPwd: String): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).resetPwd(ResetPwdReq(mobile, pwd, confirmPwd))
    }


    /**
     * 修改用户信息
     * [userIcon]: 用户头像地址
     * [userName]: 用户昵称
     * [userGender]: 用户性别
     * [userSign]: 用户签名
     */
    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<BaseResponse<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java).editUser(EditUserReq(userIcon, userName, userGender, userSign))
    }

}