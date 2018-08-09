package cn.lxw.business.user.service

import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.user.data.protocol.UserInfo
import io.reactivex.Observable


/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午11:13
 * *******************************
 * 备注： 面向接口编程，就单独用户模块这一块，接口可以抽取，而实现交由具体类实现，虽然还只有一个实现类，但需要考虑将来的变化
 * 功能描述：
 */
interface UserService {
    /**
     * 注册：
     * [mobile]：手机号
     * [verifyCode]：验证码
     * [pwd]：密码
     */
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean>


    /**
     * 登陆：
     * [mobile]：手机号/账号
     * [pwd]：密码
     * [pushId]：推送ID
     */
    fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo>

    /**
     * 忘记密码：
     * [mobile]：手机号/账号
     * [verifyCode]：验证码
     */
    fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean>

    /**
     * 重置密码：
     * [pwd]：手机号/账号
     * [confirmPwd]：验证码
     */
    fun resetPwd(mobile: String, pwd: String, confirmPwd: String): Observable<Boolean>

    /**
     * 修改用户信息
     * [userIcon]: 用户头像地址
     * [userName]: 用户昵称
     * [userGender]: 用户性别
     * [userSign]: 用户签名
     */
    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<UserInfo>


}