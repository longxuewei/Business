package cn.lxw.business.baselibrary.presenter

import android.content.Context
import cn.lxw.business.baselibrary.presenter.view.BaseView
import cn.lxw.business.baselibrary.utils.NetWorkUtils
import com.trello.rxlifecycle2.LifecycleProvider
import javax.inject.Inject

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午10:01
 * *******************************
 * 备注：
 * 功能描述：
 */
open class BasePresenter<T : BaseView> {

    /** View 层的引用 */
    lateinit var mView: T

    /** 处理Rx引起的内存泄露问题 */
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>


    @Inject
    lateinit var mContext: Context

    /**
     * 检测忘了是否可用。
     */
    fun checkNetWorkAvailable(): Boolean = NetWorkUtils.isNetWorkAvailable(mContext)

}