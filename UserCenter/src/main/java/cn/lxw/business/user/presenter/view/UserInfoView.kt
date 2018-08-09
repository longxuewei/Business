package cn.lxw.business.user.presenter.view

import cn.lxw.business.baselibrary.presenter.view.BaseView
import cn.lxw.business.user.data.protocol.UserInfo

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午10:09
 * *******************************
 * 备注：
 * 功能描述：
 */
interface UserInfoView : BaseView {
    fun onUploadTokenResult(result: String)

    fun onEditUserResult(result: UserInfo)
}