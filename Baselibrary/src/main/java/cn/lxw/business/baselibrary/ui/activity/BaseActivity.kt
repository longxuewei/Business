package cn.lxw.business.baselibrary.ui.activity

import android.os.Bundle
import cn.lxw.business.baselibrary.common.AppManager
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午10:04
 * *******************************
 * 备注：
 * 功能描述：
 */
open class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }
}