package cn.lxw.business.baselibrary.rx

import cn.lxw.business.baselibrary.presenter.view.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午11:38
 * *******************************
 * 备注：本类用于默认实现Observer。对不常用的方法进行默认的实现，以后使用这个类可以实现自己需要的方法，
 *  大多数类，都是想实现 [onNext] 方法。而不需要实现其他的方法。其实可以使用[Customer]这个类实现OnNext
 * 功能描述：
 */
open class BaseObserver<T>(private val baseView: BaseView) : Observer<T> {

    override fun onComplete() {
        baseView.hideLoading()
    }

    override fun onSubscribe(d: Disposable) {}

    override fun onNext(t: T) {}

    override fun onError(e: Throwable) {
        baseView.hideLoading()
    }
}