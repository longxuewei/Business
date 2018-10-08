package cn.lxw.business.goods.ext

import android.widget.EditText
import org.jetbrains.anko.find
import ren.qinc.numberbutton.NumberButton
import ren.qinc.numberbutton.R

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年10月08日 22:37
 * *******************************
 * 备注：SKU的加减按钮的 EditText获取
 * 功能描述：
 */


fun NumberButton.getCountEditText(): EditText = find(R.id.text_count)