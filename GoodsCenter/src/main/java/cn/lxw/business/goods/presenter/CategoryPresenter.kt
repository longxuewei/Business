package cn.lxw.business.goods.presenter

import cn.lxw.business.baselibrary.ext.execute
import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.baselibrary.rx.BaseObserver
import cn.lxw.business.goods.data.protocol.Category
import cn.lxw.business.goods.presenter.view.CategoryView
import cn.lxw.business.goods.service.CategoryService
import com.orhanobut.logger.Logger
import javax.inject.Inject

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月10日 下午10:09
 * *******************************
 * 备注： 注册界面的Presenter
 * 功能描述：
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {

    @Inject
    lateinit var categoryService: CategoryService


    fun getCategory(parentId: Int) {
        if (!checkNetWorkAvailable()) {
            return
        }
        mView.showLoading()
        categoryService.getCategory(parentId).execute(object : BaseObserver<MutableList<Category>?>(mView) {
            override fun onNext(t: MutableList<Category>?) {
                mView.onGetCategoryResult(t)
            }

            override fun onComplete() {
                super.onComplete()
                Logger.d("onComplete完成")
                mView.hideLoading()
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                Logger.e("onError错误:${e.message}")
                onNext(null)
                mView.hideLoading()
            }
        }, lifecycleProvider)
    }
}