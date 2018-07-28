package cn.lxw.business.user.presenter

import android.util.Log
import cn.lxw.business.baselibrary.ext.execute
import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.baselibrary.rx.BaseObserver
import cn.lxw.business.user.presenter.view.ForgetPwdView
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
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, verifyCode: String) {
        if (!checkNetWorkAvailable()) {
            Log.d("TAG", "网络不可用")
            return
        }
        mView.showLoading()
        userService.forgetPwd(mobile, verifyCode).execute(object : BaseObserver<String>(mView) {
            override fun onNext(t: String) {
                super.onNext(t)
                mView.onForgetPwdResult(t)
            }
        }, lifecycleProvider)
    }
}