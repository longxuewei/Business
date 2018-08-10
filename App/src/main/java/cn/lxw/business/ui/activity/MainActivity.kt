package cn.lxw.business.ui.activity

import android.os.Bundle
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ui.activity.BaseActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mNavigationNb.checkCartBadge(20)
                }


        Observable.timer(7, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mNavigationNb.checkMessageBadge(true)
                }
    }
}