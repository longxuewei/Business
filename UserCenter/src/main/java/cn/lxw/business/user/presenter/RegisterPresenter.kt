package cn.lxw.business.user.presenter

import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.user.presenter.view.RegisterView

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午10:09
 * *******************************
 * 备注：
 * 功能描述：
 */
class RegisterPresenter:BasePresenter<RegisterView>() {
    fun register(mobile:String,verifyCode:String){
        mView.onRegisterResult(true)
    }
}