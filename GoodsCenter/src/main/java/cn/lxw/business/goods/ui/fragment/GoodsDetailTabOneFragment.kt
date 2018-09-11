package cn.lxw.business.goods.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.lxw.business.baselibrary.ext.onClick
import cn.lxw.business.baselibrary.ui.activity.BaseMvpFragment
import cn.lxw.business.baselibrary.widget.BannerImageLoader
import cn.lxw.business.goods.R
import cn.lxw.business.goods.common.GoodsConstant
import cn.lxw.business.goods.injection.component.DaggerGoodsComponent
import cn.lxw.business.goods.injection.module.GoodsModule
import cn.lxw.business.goods.presenter.GoodsDetailPresenter
import cn.lxw.business.goods.presenter.view.GoodsDetailView
import cn.lxw.business.goods.ui.activity.GoodsDetailActivity
import cn.lxw.business.goods.weight.GoodsSkuPopView
import com.eightbitlab.rxbus.Bus
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.event.GoodsDetailImageEvent
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月13日 22:43
 * *******************************
 * 备注：
 * 功能描述：
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {


    private lateinit var mSkuPopView: GoodsSkuPopView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initView()
        initSkuPop()
        loadData()
    }

    private fun initView() {
        mSkuView.onClick {
            mSkuPopView.showAtLocation((activity as GoodsDetailActivity).window.decorView.rootView, Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL, 0, 0)
        }
    }

    private fun initSkuPop() {
        mSkuPopView = GoodsSkuPopView(activity as GoodsDetailActivity)
    }

    private fun initBanner() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2500)
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)
    }

    private fun loadData() {
        presenter.getGoodsDetail(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().goodsModule(GoodsModule()).activityComponent(activityComponent).build().inject(this)
        presenter.mView = this
    }

    override fun onGetGoodsDetailResult(result: Goods) {
        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadSkuPopData(result)
    }

    /**
     * 数据到达之后,将sku的数据设置上去
     */
    private fun loadSkuPopData(result: Goods) {
        mSkuPopView.setGoodsCode(result.goodsCode)
        mSkuPopView.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPopView.setGoodsPrice(result.goodsDefaultPrice)
        mSkuPopView.setSkuData(result.goodsSku)
    }

}