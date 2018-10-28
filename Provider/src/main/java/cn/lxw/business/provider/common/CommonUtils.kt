package cn.lxw.business.provider.common

import cn.lxw.business.baselibrary.common.BaseConstant
import cn.lxw.business.baselibrary.utils.AppPrefsUtils
import cn.lxw.business.provider.router.RouterPath
import com.alibaba.android.arouter.launcher.ARouter

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


/**
 * 该方法用于检测调用某个方法之前需要做登录判断的封装。
 */
fun afterLogin(method: () -> Unit) {
    if (isLogined()) {
        method()
    } else {
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
    }
}