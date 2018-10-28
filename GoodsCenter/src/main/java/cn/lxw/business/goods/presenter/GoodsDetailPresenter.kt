package cn.lxw.business.goods.presenter

import android.util.Log
import cn.lxw.business.baselibrary.ext.execute
import cn.lxw.business.baselibrary.presenter.BasePresenter
import cn.lxw.business.baselibrary.rx.BaseObserver
import cn.lxw.business.goods.data.protocol.Goods
import cn.lxw.business.goods.presenter.view.GoodsDetailView
import cn.lxw.business.goods.service.CartService
import cn.lxw.business.goods.service.GoodsService
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
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailView>() {

    @Inject
    lateinit var goodsService: GoodsService

    @Inject
    lateinit var mCartService: CartService

    fun getGoodsDetail(goodsId: Int) {
        if (!checkNetWorkAvailable()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId).execute(object : BaseObserver<Goods>(mView) {
            override fun onNext(t: Goods) {
                mView.onGetGoodsDetailResult(t)
            }
        }, lifecycleProvider)

    }

    /**
     * 添加商品到购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String) {
        mCartService.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice, goodsCount, goodsSku).execute(object : BaseObserver<Int>(mView) {
            override fun onNext(t: Int) {
                mView.onAddCartResult(t)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                Log.d("TAG:", e.message)
            }
        }, lifecycleProvider)
    }


}