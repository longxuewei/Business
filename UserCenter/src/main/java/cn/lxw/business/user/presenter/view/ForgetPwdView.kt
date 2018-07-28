package cn.lxw.business.user.presenter.view

import cn.lxw.business.baselibrary.presenter.view.BaseView

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午10:09
 * *******************************
 * 备注：忘记密码页面回调
 * 功能描述：
 */
interface ForgetPwdView : BaseView {
    fun onForgetPwdResult(result: String)
}