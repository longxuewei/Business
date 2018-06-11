package cn.lxw.business.user.data.api

import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.user.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月11日 下午11:41
 * *******************************
 * 备注：
 * 功能描述：
 */
interface UserApi {
    @POST("/5b1e99063100008a233ff96d")
    fun register(@Body req: RegisterReq): Observable<BaseResponse<String>>
}