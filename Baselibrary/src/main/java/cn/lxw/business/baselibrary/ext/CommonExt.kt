package cn.lxw.business.baselibrary.ext

import cn.lxw.business.baselibrary.rx.BaseObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午11:39
 * *******************************
 * 备注： 扩展方法
 * 功能描述：
 */

fun <T> Observable<T>.execute(subscribe: BaseObserver<T>) {
    this.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(subscribe)
}