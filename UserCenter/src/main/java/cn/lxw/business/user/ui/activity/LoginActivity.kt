package cn.lxw.business.user.ui.activity

import android.os.Bundle
import android.view.View
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ext.enable
import cn.lxw.business.baselibrary.ext.onClick
import cn.lxw.business.baselibrary.ui.activity.BaseMvpActivity
import cn.lxw.business.user.data.protocol.UserInfo
import cn.lxw.business.user.injection.component.DaggerUserComponent
import cn.lxw.business.user.presenter.LoginPresenter
import cn.lxw.business.user.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 ***************************************************
 * 猿代码：Lxw
 * Email： China2021@126.com
 * 时间轴：2018年06月08日 17:38
 ***************************************************
 * 备注：登陆界面
 * 功能描述：
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }


    /**
     * 登陆回调
     */
    override fun onLoginResult(result: UserInfo) {
        toast(result.toString())

    }


    /**
     * 点击事件
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btLogin -> {
                presenter.login(etMobile.text.toString(), etPwd.text.toString(), "")
            }
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }
        }
    }


    /**
     * 初始化视图
     */
    private fun initView() {
        //按钮控制
        btLogin.enable(etMobile, { isButtonEnable() })
        btLogin.enable(etPwd, { isButtonEnable() })
        btLogin.onClick(this)
        mHeaderBar.getRightTextView().onClick(this)
    }


    /**
     * 判断登陆按钮是否可用
     */
    private fun isButtonEnable(): Boolean {
        return etMobile.text.toString().isNotEmpty() && etPwd.text.toString().isNotEmpty()
    }


    /**
     * 实现依赖注入
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        presenter.mView = this
    }
}