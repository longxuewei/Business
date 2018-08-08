package cn.lxw.business.user.service.impl

import cn.lxw.business.baselibrary.ext.convert
import cn.lxw.business.user.data.repository.UploadRepository
import cn.lxw.business.user.service.UploadService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午11:14
 * *******************************
 * 备注：七牛云存储的Service实现
 * 功能描述：
 */
class UploadServiceImpl @Inject constructor() : UploadService {


    @Inject
    lateinit var repository: UploadRepository

    /**
     * 获取上传Token
     */
    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken().convert()
    }


}