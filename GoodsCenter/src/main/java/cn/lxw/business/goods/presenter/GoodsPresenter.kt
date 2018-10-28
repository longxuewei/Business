package cn.lxw.business.goods.presenter

import cn.lxw.business.baselibrary.ext.execute
import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.baselibrary.rx.BaseObserver
import cn.lxw.business.goods.data.protocol.Goods
import cn.lxw.business.goods.presenter.view.GoodsListView
import cn.lxw.business.goods.service.GoodsService
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
class GoodsPresenter @Inject constructor() : BasePresenter<GoodsListView>() {

    @Inject
    lateinit var goodsService: GoodsService


    fun getGoodsList(categoryId: Int, pageNo: Int) {
        if (!checkNetWorkAvailable()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsList(categoryId, pageNo).execute(object : BaseObserver<MutableList<Goods>?>(mView) {
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGoodsListResult(t)
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


    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {
        if (!checkNetWorkAvailable()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsListByKeyword(keyword, pageNo).execute(object : BaseObserver<MutableList<Goods>?>(mView) {
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGoodsListResult(t)
            }
        }, lifecycleProvider)

    }
}