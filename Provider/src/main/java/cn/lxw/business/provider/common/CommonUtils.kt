package cn.lxw.business.provider.common

import cn.lxw.business.baselibrary.common.BaseConstant
import cn.lxw.business.baselibrary.utils.AppPrefsUtils

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月13日 21:33
 * *******************************
 * 备注：
 * 功能描述：
 */

/**
 * 顶级函数,是否登录,用过SP中的Token为空作为判断依据
 */
fun isLogined() = AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN).isNotEmpty()
