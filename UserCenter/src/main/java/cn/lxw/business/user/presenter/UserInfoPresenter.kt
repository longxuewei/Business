package cn.lxw.business.user.presenter

import cn.lxw.business.baselibrary.ext.execute
import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.baselibrary.rx.BaseObserver
import cn.lxw.business.user.presenter.view.UserInfoView
import cn.lxw.business.user.service.UploadService
import cn.lxw.business.user.service.UserService
import javax.inject.Inject

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午10:09
 * *******************************
 * 备注： 用户信息界面的Presenter
 * 功能描述：
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var uploadService: UploadService


    /**
     * 获取上传Token
     */
    fun getUploadToken() {
        if (!checkNetWorkAvailable())
            return
        uploadService.getUploadToken().execute(object : BaseObserver<String>(mView) {
            override fun onNext(t: String) {
                super.onNext(t)
                mView.getUploadTokenResult(t)
            }
        }, lifecycleProvider)
    }

}