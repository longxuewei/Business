package cn.lxw.business.baselibrary.widget

import android.content.Context
import android.util.AttributeSet
import cn.lxw.business.baselibrary.R
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem

/**
 ***************************************************
 * 猿代码： Lxw
 * Email： China2021@126.com
 * 时间轴： 2018年08月10日 14:48
 ***************************************************
 * 备注：封装 官方的 BottomNavigationBar
 * 功能描述：
 */
class NavigationBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {


    /** 购入车标签 */
    private val mCartBadge by lazy { TextBadgeItem().apply { this.hide() } }

    /** 消息中心标签 */
    private val mMsgBadge by lazy {
        ShapeBadgeItem().apply {
            this.setShape(ShapeBadgeItem.SHAPE_OVAL)
            this.hide()
        }
    }


    /**
     * 初始化 主页底部的导航栏.
     */
    init {

        //主页
        val homeItem = BottomNavigationItem(R.drawable.btn_nav_home_press, resources.getString(R.string.nav_bar_home))
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        //分类
        val categoryItem = BottomNavigationItem(R.drawable.btn_nav_category_press, resources.getString(R.string.nav_bar_category))
                .setInactiveIconResource(R.drawable.btn_nav_category_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
        //购物车
        val cartItem = BottomNavigationItem(R.drawable.btn_nav_cart_press, resources.getString(R.string.nav_bar_cart))
                .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)
                .apply { this.setBadgeItem(mCartBadge) }


        //消息
        val msgItem = BottomNavigationItem(R.drawable.btn_nav_msg_press, resources.getString(R.string.nav_bar_msg))
                .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal).apply {
                    this.setBadgeItem(mMsgBadge)
                }
        //我的
        val userItem = BottomNavigationItem(R.drawable.btn_nav_user_press, resources.getString(R.string.nav_bar_user))
                .setInactiveIconResource(R.drawable.btn_nav_user_normal)
                .setActiveColorResource(R.color.common_blue)
                .setInActiveColorResource(R.color.text_normal)

        setMode(BottomNavigationBar.MODE_FIXED)
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)

        //加入Item
        addItem(homeItem)
                .addItem(categoryItem)
                .addItem(cartItem)
                .addItem(msgItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)
                .initialise()
    }


    /**
     * 设置购物车数量
     * [count]: 消息数量
     */
    fun checkCartBadge(count: Int) {
        if (count == 0) {
            mCartBadge.hide()
        } else {
            mCartBadge.show()
            mCartBadge.setText("$count")
        }
    }


    /**
     * 设置消息中心是否有新消息
     * [isShow]: 是否显示
     */
    fun checkMessageBadge(isShow: Boolean) {
        if (isShow) {
            mMsgBadge.show()
        } else {
            mMsgBadge.hide()
        }
    }
}