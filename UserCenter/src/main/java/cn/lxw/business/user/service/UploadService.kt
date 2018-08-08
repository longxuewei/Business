package cn.lxw.business.user.service

import io.reactivex.Observable


/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午11:13
 * *******************************
 * 备注： 七牛云上传服务
 */
interface UploadService {

    /**
     * 获取上传凭证
     */
    fun getUploadToken(): Observable<String>

}