package cn.lxw.business.user.data.repository

import cn.lxw.business.baselibrary.data.net.RetrofitFactory
import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.user.data.api.UploadApi
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
class UploadRepository @Inject constructor() {


    /**
     * 获取七牛云存储的上传Token
     */
    fun getUploadToken(): Observable<BaseResponse<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java).getUploadToken()
    }

}