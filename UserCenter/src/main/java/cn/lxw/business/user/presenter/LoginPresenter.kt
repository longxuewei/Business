package cn.lxw.business.user.presenter

import android.util.Log
import cn.lxw.business.baselibrary.ext.execute
import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.baselibrary.rx.BaseObserver
import cn.lxw.business.user.data.protocol.UserInfo
import cn.lxw.business.user.presenter.view.LoginView
import cn.lxw.business.user.service.UserService
import javax.inject.Inject

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午10:09
 * *******************************
 * 备注： 注册界面的Presenter
 * 功能描述：
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserService


    /**
     * 登陆：
     * [mobile]：手机号/账号
     * [pwd]：密码
     * [pushId]：推送ID
     */
    fun login(mobile: String, pwd: String, pushId: String) {
        if (!checkNetWorkAvailable()) {
            Log.d("TAG", "网络不可用")
            return
        }
        mView.showLoading()
        userService.login(mobile, pwd, pushId).execute(object : BaseObserver<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                super.onNext(t)
                mView.onLoginResult(t)
            }
        }, lifecycleProvider)
    }
}