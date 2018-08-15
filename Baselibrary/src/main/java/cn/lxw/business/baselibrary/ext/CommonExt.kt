package cn.lxw.business.baselibrary.ext

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import cn.lxw.business.baselibrary.R
import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.baselibrary.rx.BaseFuncBoolean
import cn.lxw.business.baselibrary.rx.BaseObserver
import cn.lxw.business.baselibrary.widget.DefaultTextWatcher
import com.kennyc.view.MultiStateView
import com.kotlin.base.utils.GlideUtils
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.find

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午11:39
 * *******************************
 * 备注： 扩展Observable的方法，目的是在此提供了线程切换，使观察者在主线程，而订阅在io线程，这样就不需要频繁的切换线程了
 * 功能描述：
 */

fun <T> Observable<T>.execute(subscribe: BaseObserver<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscribe)
}


fun <T> Observable<BaseResponse<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

fun <T> Observable<BaseResponse<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

/**
 * 扩展View的点击事件。
 */
fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}


/**
 * 扩展View的点击事件，使用高阶函数的方式。传入方法调用
 */
fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}


/**
 * 扩展Button是否可以用
 */
fun Button.enable(editText: EditText, method: () -> Boolean) {
    val button = this
    editText.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            button.isEnabled = method()
        }
    })
}

/**
 * 扩展所有的ImageView拥有加载显示图片的功能
 * [url]: 图片URL
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}


/**
 * 多状态视图的封装
 */
fun MultiStateView.startLoading() {
    viewState = MultiStateView.VIEW_STATE_LOADING
    (this.find<View>(R.id.loading_anim_view).background as AnimationDrawable).start()
}


/**
 * 设置是否隐藏
 */
fun View.setVisibility(isGone: Boolean) {
    this.visibility = if (isGone) View.GONE else View.VISIBLE
}
