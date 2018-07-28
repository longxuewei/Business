package cn.lxw.business.user.ui.activity

import android.os.Bundle
import android.view.View
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ext.enable
import cn.lxw.business.baselibrary.ext.onClick
import cn.lxw.business.baselibrary.ui.activity.BaseMvpActivity
import cn.lxw.business.user.injection.component.DaggerUserComponent
import cn.lxw.business.user.presenter.ResetPwdPresenter
import cn.lxw.business.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
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
class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView, View.OnClickListener {

    override fun onResetPwdResult(result: String) {
        toast(result)
    }

    override fun onClick(v: View) {
        when (v.id) {
        //确认修改密码
            R.id.btConfirm -> {
                btConfirm.onClick { presenter.resetPwd(etPwd.text.toString(), etConfirmPwd.text.toString()) }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        //按钮控制
        btConfirm.enable(etPwd, { isButtonEnable() })
        btConfirm.enable(etConfirmPwd, { isButtonEnable() })
        btConfirm.onClick(this)
    }


    /**
     * 判断注册按钮是否可用
     */
    private fun isButtonEnable(): Boolean {
        return etPwd.text.toString().isNotEmpty() && etConfirmPwd.text.toString().isNotEmpty()
    }


    /**
     * 实现依赖注入
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).build().inject(this)
        presenter.mView = this
    }
}