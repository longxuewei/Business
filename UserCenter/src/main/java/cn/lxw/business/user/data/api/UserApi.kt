package cn.lxw.business.user.data.api

import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.user.data.protocol.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月11日 下午11:41
 * *******************************
 * 备注：具体Retrofit所需要的接口层，用户模块的API
 * 功能描述：
 */
interface UserApi {

    @POST("userCenter/register")
    fun register(@Body req: RegisterReq): Observable<BaseResponse<String>>

    @POST("userCenter/login")
    fun login(@Body req: LoginReq): Observable<BaseResponse<UserInfo>>

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req: ForgetPwdReq): Observable<BaseResponse<String>>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req: ResetPwdReq): Observable<BaseResponse<String>>

    @POST("userCenter/editUser")
    fun editUser(@Body req: EditUserReq): Observable<BaseResponse<UserInfo>>


}