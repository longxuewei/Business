package cn.lxw.business.user.injection.module

import cn.lxw.business.user.service.UploadService
import cn.lxw.business.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月21日 下午10:12
 * *******************************
 * 备注：
 * 功能描述：
 */
@Module
class UploadModule {

    @Provides
    fun providesUploadService(uploadServiceImpl: UploadServiceImpl): UploadService {
        return uploadServiceImpl
    }
}