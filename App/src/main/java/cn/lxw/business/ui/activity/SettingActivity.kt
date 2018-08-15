package cn.lxw.business.ui.activity

import UserPrefsUtils
import android.os.Bundle
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ext.onClick
import cn.lxw.business.baselibrary.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月13日 21:39
 * *******************************
 * 备注：
 * 功能描述：
 */
class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        initView()
    }

    private fun initView() {
        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}