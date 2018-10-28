package cn.lxw.business.goods.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.Toast
import cn.lxw.business.baselibrary.ext.onClick
import cn.lxw.business.baselibrary.ui.activity.BaseActivity
import cn.lxw.business.baselibrary.ui.activity.BaseMvpFragment
import cn.lxw.business.baselibrary.utils.YuanFenConverter
import cn.lxw.business.baselibrary.widget.BannerImageLoader
import cn.lxw.business.goods.R
import cn.lxw.business.goods.common.GoodsConstant
import cn.lxw.business.goods.data.protocol.Goods
import cn.lxw.business.goods.event.AddCartEvent
import cn.lxw.business.goods.event.GoodsDetailImageEvent
import cn.lxw.business.goods.event.SkuChangedEvent
import cn.lxw.business.goods.injection.component.DaggerGoodsComponent
import cn.lxw.business.goods.injection.module.GoodsModule
import cn.lxw.business.goods.presenter.GoodsDetailPresenter
import cn.lxw.business.goods.presenter.view.GoodsDetailView
import cn.lxw.business.goods.ui.activity.GoodsDetailActivity
import cn.lxw.business.goods.weight.GoodsSkuPopView
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.jetbrains.anko.contentView

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


    private lateinit var mAnimationStart: ScaleAnimation

    private lateinit var mAnimationEnd: ScaleAnimation

    private lateinit var mSkuPopView: GoodsSkuPopView

    private var mCurrentGoods: Goods? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initView()
        initAnim()
        initSkuPop()
        loadData()
        initObserve()

    }

    private fun initView() {
        mSkuView.onClick {
            mSkuPopView.showAtLocation((activity as GoodsDetailActivity).window.decorView.rootView, Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL, 0, 0)
            (activity as BaseActivity).contentView?.startAnimation(mAnimationStart)
        }
    }

    private fun initSkuPop() {
        mSkuPopView = GoodsSkuPopView(activity as GoodsDetailActivity)
        mSkuPopView.setOnDismissListener {
            (activity as BaseActivity).contentView?.startAnimation(mAnimationEnd)
        }
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
        mCurrentGoods = result
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


    private fun initObserve() {
        Bus.observe<SkuChangedEvent>().subscribe {
            mSkuSelectedTv.text = mSkuPopView.getSelectSku() + GoodsConstant.SKU_SEPARATOR + mSkuPopView.getSelectCount() + "件"
        }.registerInBus(this)


        Bus.observe<AddCartEvent>().subscribe {
            mCurrentGoods?.let {
                presenter.addCart(it.id,
                        it.goodsDesc,
                        it.goodsDefaultIcon,
                        it.goodsDefaultPrice,
                        mSkuPopView.getSelectCount(),
                        mSkuPopView.getSelectSku())
            }
        }.registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }


    /*
              初始化缩放动画
           */
    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }


    override fun onAddCartResult(result: Int) {
        Toast.makeText(activity, "add_cart-$result", Toast.LENGTH_LONG).show()
    }
}