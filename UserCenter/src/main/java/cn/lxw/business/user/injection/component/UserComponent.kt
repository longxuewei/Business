package cn.lxw.business.user.injection.component

import cn.lxw.business.baselibrary.injection.PerComponentScope
import cn.lxw.business.baselibrary.injection.component.ActivityComponent
import cn.lxw.business.user.injection.module.UserModule
import cn.lxw.business.user.ui.activity.LoginActivity
import cn.lxw.business.user.ui.activity.RegisterActivity
import dagger.Component

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月21日 下午10:14
 * *******************************
 * 备注：
 * 功能描述：
 */
@PerComponentScope
@Component(modules = [(UserModule::class)], dependencies = [ActivityComponent::class])
interface UserComponent {
    fun inject(registerActivity: RegisterActivity)
    fun inject(loginActivity: LoginActivity)
}