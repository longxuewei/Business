package cn.lxw.business.user.presenter

import android.util.Log
import cn.lxw.business.baselibrary.ext.execute
import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.baselibrary.rx.BaseObserver
import cn.lxw.business.user.presenter.view.ResetPwdView
import cn.lxw.business.user.service.UserService
import com.orhanobut.logger.Logger
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
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun resetPwd(mobile: String, pwd: String, confirmPwd: String) {
        if (!checkNetWorkAvailable()) {
            Logger.d("网络不可用")
            return
        }
        mView.showLoading()
        userService.resetPwd(mobile, pwd, confirmPwd).execute(object : BaseObserver<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                super.onNext(t)
                if (t)
                    mView.onResetPwdResult("验证成功")
            }
        }, lifecycleProvider)
    }
}