package cn.lxw.business.baselibrary.ext

import android.view.View

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月15日 23:16
 * *******************************
 * 备注：
 * 功能描述：
 */


/**
 * 顶级函数,批量设置View的显示和隐藏
 */
fun setGone(vararg views: View, isGone: Boolean) {
    views.forEach {
        it.setVisibility(isGone)
    }
}