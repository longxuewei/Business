package cn.lxw.business.ui.activity

import android.os.Bundle
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ui.activity.BaseActivity
import cn.lxw.business.ui.fragment.HomeFragment

/**
 ***************************************************
 * 猿代码：Lxw
 * Email： China2021@126.com
 * 时间轴：2018年06月08日 17:00
 ***************************************************
 * 备注：
 * 功能描述：
 */
class MainActivity : BaseActivity() {

    private val mHomeFragment by lazy { HomeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction().replace(R.id.homeContainer, mHomeFragment).commit()
    }
}