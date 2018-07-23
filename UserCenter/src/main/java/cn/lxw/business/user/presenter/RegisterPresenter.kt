package cn.lxw.business.user.presenter

import android.util.Log
import cn.lxw.business.baselibrary.ext.execute
import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.baselibrary.rx.BaseObserver
import cn.lxw.business.user.presenter.view.RegisterView
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
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, verifyCode: String) {


        if (!checkNetWorkAvailable()) {
            Log.d("TAG", "网络不可用")
            return
        }


        mView.showLoading()

        userService.register("", "", "").execute(object : BaseObserver<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                super.onNext(t)
                mView.onRegisterResult(t)
            }
        }, lifecycleProvider)
    }
}