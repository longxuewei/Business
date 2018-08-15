package cn.lxw.business.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.lxw.business.R
import cn.lxw.business.baselibrary.common.*
import cn.lxw.business.baselibrary.ui.activity.BaseFragment
import cn.lxw.business.baselibrary.widget.BannerImageLoader
import cn.lxw.business.ui.adapter.HomeDiscountAdapter
import cn.lxw.business.ui.adapter.TopicAdapter
import com.kotlin.mall.common.*
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow

/**
 ***************************************************
 * 猿代码： Lxw
 * Email： China2021@126.com
 * 时间轴： 2018年08月10日 16:05
 ***************************************************
 * 备注：主页
 * 功能描述：
 */
class HomeFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initNewsData()
        initDiscount()
        initTopic()
    }


    /**
     * 初始化公告
     */
    private fun initNewsData() {
        mNewsFlipperView.setData(arrayOf("这是第一条通知", "这是第二条通知"))
    }


    /**
     * 初始化Banner
     */
    private fun initBanner() {
        mHomeBannerHb.setImageLoader(BannerImageLoader())
        mHomeBannerHb.setImages(mutableListOf(IMAGE_1, IMAGE_2, IMAGE_3, IMAGE_4, IMAGE_5, IMAGE_6, IMAGE_7, IMAGE_8))
        mHomeBannerHb.setBannerAnimation(Transformer.Accordion)
        mHomeBannerHb.setDelayTime(2500)
        mHomeBannerHb.setIndicatorGravity(BannerConfig.RIGHT)
        mHomeBannerHb.start()
    }

    /**
     * 初始化折扣
     */
    private fun initDiscount() {
        val discountAdapter = HomeDiscountAdapter(activity)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mHomeDiscountRv.layoutManager = layoutManager
        mHomeDiscountRv.adapter = discountAdapter
        discountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
    }

    private fun initTopic() {
        mTopicPager.adapter = TopicAdapter(activity, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5
        CoverFlow.Builder().with(mTopicPager).scale(0.3f).pagerMargin(-30f).spaceSize(0.0f).build()

    }
}