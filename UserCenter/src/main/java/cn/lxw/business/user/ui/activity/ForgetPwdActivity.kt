package cn.lxw.business.user.ui.activity

import android.os.Bundle
import android.view.View
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ext.enable
import cn.lxw.business.baselibrary.ext.onClick
import cn.lxw.business.baselibrary.ui.activity.BaseMvpActivity
import cn.lxw.business.user.injection.component.DaggerUserComponent
import cn.lxw.business.user.presenter.ForgetPwdPresenter
import cn.lxw.business.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 ***************************************************
 * 猿代码：Lxw
 * Email： China2021@126.com
 * 时间轴：2018年06月08日 17:38
 ***************************************************
 * 备注：忘记密码
 * 功能描述：
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    override fun onForgetPwdResult(result: String) {
        toast(result)
        startActivity<ResetPwdActivity>()
    }

    override fun onClick(v: View) {
        when (v.id) {
        //下一步
            R.id.btNext -> {
                //TODO 验证 验证码是否一致
                btNext.onClick { presenter.register(etMobile.text.toString(), etVerifyCode.text.toString()) }
            }

        //获取验证码
            R.id.btGetVerifyCode -> {
                btGetVerifyCode.requestSendVerifyNumber()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        initView()


    }

    /**
     * 初始化视图
     */
    private fun initView() {
        //按钮控制
        btNext.enable(etMobile, { isButtonEnable() })
        btNext.enable(etVerifyCode, { isButtonEnable() })
        btGetVerifyCode.onClick(this)
        btNext.onClick(this)
    }


    /**
     * 判断注册按钮是否可用
     */
    private fun isButtonEnable(): Boolean {
        return etMobile.text.toString().isNotEmpty() && etVerifyCode.text.toString().isNotEmpty()
    }


    /**
     * 实现依赖注入
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        presenter.mView = this
    }
}