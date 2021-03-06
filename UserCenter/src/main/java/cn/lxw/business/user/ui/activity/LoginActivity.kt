package cn.lxw.business.user.ui.activity

import UserPrefsUtils
import android.os.Bundle
import android.view.View
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ext.enable
import cn.lxw.business.baselibrary.ext.onClick
import cn.lxw.business.baselibrary.ui.activity.BaseMvpActivity
import cn.lxw.business.provider.router.RouterPath
import cn.lxw.business.user.data.protocol.UserInfo
import cn.lxw.business.user.injection.component.DaggerUserComponent
import cn.lxw.business.user.presenter.LoginPresenter
import cn.lxw.business.user.presenter.view.LoginView
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

/**
 ***************************************************
 * 猿代码：Lxw
 * Email： China2021@126.com
 * 时间轴：2018年06月08日 17:38
 ***************************************************
 * 备注：登陆界面
 * 功能描述：
 */
@Route(path = RouterPath.UserCenter.PATH_LOGIN)
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
        UserPrefsUtils.putUserInfo(result)
        finish()
    }


    /**
     * 点击事件
     */
    override fun onClick(v: View) {
        when (v.id) {

        //登陆
            R.id.btLogin -> {
                presenter.login(etMobile.text.toString(), etPwd.text.toString(), "")
            }

        //注册
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }
        //忘记密码
            R.id.tvForgetPwd -> startActivity<ForgetPwdActivity>()
        }
    }


    /**
     * 初始化视图
     */
    private fun initView() {
        //按钮控制
        btLogin.enable(etMobile) { isButtonEnable() }
        btLogin.enable(etPwd) { isButtonEnable() }
        btLogin.onClick(this)
        mHeaderBar.getRightTextView().onClick(this)
        tvForgetPwd.onClick(this)
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