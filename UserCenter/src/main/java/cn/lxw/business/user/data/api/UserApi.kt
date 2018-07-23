package cn.lxw.business.user.data.api

import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.user.data.protocol.RegisterReq
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月11日 下午11:41
 * *******************************
 * 备注：具体Retrofit所需要的接口层
 * 功能描述：
 */
interface UserApi {

    @POST("userCenter/register")
    fun register(@Body req: RegisterReq): Observable<BaseResponse<String>>
}