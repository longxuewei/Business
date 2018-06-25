package cn.lxw.business.user.injection.module

import cn.lxw.business.user.service.UserService
import cn.lxw.business.user.service.impl.UserServiceImpl
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
class UserModule {

    @Provides
    fun providesUserService(userServiceImpl: UserServiceImpl):UserService{
        return userServiceImpl
    }
}