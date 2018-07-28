package cn.lxw.business.user.ui.activity

import android.os.Bundle
import android.view.View
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ext.enable
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
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id) {
        //注册
            R.id.btRegister -> {
                btRegister.onClick { presenter.register(etMobile.text.toString(), etPwd.text.toString(), etVerifyCode.text.toString()) }
            }

        //获取验证码
            R.id.btGetVerifyCode -> {
                btGetVerifyCode.requestSendVerifyNumber()
            }
        }
    }


    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initView()


    }

    /**
     * 初始化视图
     */
    private fun initView() {
        //按钮控制
        btRegister.enable(etMobile, { isButtonEnable() })
        btRegister.enable(etVerifyCode, { isButtonEnable() })
        btRegister.enable(etPwd, { isButtonEnable() })
        btRegister.enable(etConfirmPwd, { isButtonEnable() })
        btGetVerifyCode.onClick(this)
        btRegister.onClick(this)
    }


    /**
     * 判断注册按钮是否可用
     */
    private fun isButtonEnable(): Boolean {
        return etMobile.text.toString().isNotEmpty() && etVerifyCode.text.toString().isNotEmpty() && etPwd.text.toString().isNotEmpty() && etConfirmPwd.text.toString().isNotEmpty()
    }


    /**
     * 实现依赖注入
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        presenter.mView = this
    }
}