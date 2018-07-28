package cn.lxw.business.baselibrary.widget

import android.text.Editable
import android.text.TextWatcher

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018 年 07 月 28 日 14:31
 * *******************************
 * 备注：实现系统的默认的三个方法。 方便子类实现自己需要的方法。
 * 功能描述：
 */
open class DefaultTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable) {}
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
}