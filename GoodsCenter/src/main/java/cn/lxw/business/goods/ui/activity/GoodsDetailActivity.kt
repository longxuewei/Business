package cn.lxw.business.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import cn.lxw.business.baselibrary.ui.activity.BaseActivity
import cn.lxw.business.goods.R
import cn.lxw.business.goods.ui.adapter.GoodsDetailVpAdapter
import kotlinx.android.synthetic.main.activity_goods_detail.*

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月19日 21:33
 * *******************************
 * 备注：
 * 功能描述：
 */
class GoodsDetailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)
        initView()
    }

    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)
    }
}