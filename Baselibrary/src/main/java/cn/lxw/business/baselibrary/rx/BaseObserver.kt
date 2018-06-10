package cn.lxw.business.baselibrary.rx

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午11:38
 * *******************************
 * 备注：
 * 功能描述：
 */
open class BaseObserver<T> : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {
    }
}