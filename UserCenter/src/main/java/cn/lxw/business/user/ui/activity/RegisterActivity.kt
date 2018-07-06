package cn.lxw.business.user.ui.activity

import android.os.Bundle
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ext.onClick
import cn.lxw.business.baselibrary.ui.activity.BaseMvpActivity
import cn.lxw.business.user.injection.component.DaggerUserComponent
import cn.lxw.business.user.presenter.RegisterPresenter
import cn.lxw.business.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 ***************************************************
 * 猿代码：Lxw
 * Email： China2021@126.com
 * 时间轴：2018年06月08日 17:38
 ***************************************************
 * 备注：
 * 功能描述：
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {
    override fun onRegisterResult(result: Boolean) {
        toast(if (result) "注册成功" else "注册失败")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initInjection()
        btCommit.onClick { presenter.register("", "") }
    }

    private fun initInjection() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        presenter.mView = this
    }
}