package cn.lxw.business.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ui.activity.BaseActivity
import cn.lxw.business.goods.ui.fragment.CategoryFragment
import cn.lxw.business.ui.fragment.HomeFragment
import cn.lxw.business.ui.fragment.MeFragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

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

    private val mFragments = Stack<Fragment>()

    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment by lazy { HomeFragment() }
    private val mMsgFragment by lazy { HomeFragment() }
    private val mMeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        initBottomNavigationBar()
        changeFragment(0)
    }

    /**
     * 初始化Fragment
     */
    private fun initFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.homeContainer, mHomeFragment)
                .add(R.id.homeContainer, mCategoryFragment)
                .add(R.id.homeContainer, mCartFragment)
                .add(R.id.homeContainer, mMsgFragment)
                .add(R.id.homeContainer, mMeFragment)
                .commit()
        mFragments.add(mHomeFragment)
        mFragments.add(mCategoryFragment)
        mFragments.add(mCartFragment)
        mFragments.add(mMsgFragment)
        mFragments.add(mMeFragment)
    }


    /**
     * 动态显影Fragment
     * [position]: 位置
     */
    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        mFragments.forEach {
            manager.hide(it)
        }
        manager.show(mFragments[position])
        manager.commit()
    }


    /**
     * 初始化导航栏按钮
     */
    private fun initBottomNavigationBar() {
        mNavigationNb.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {}

            override fun onTabUnselected(position: Int) {}

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })
    }
}