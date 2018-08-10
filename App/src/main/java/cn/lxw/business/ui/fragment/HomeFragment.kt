package cn.lxw.business.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.lxw.business.R
import cn.lxw.business.baselibrary.common.*
import cn.lxw.business.baselibrary.ui.activity.BaseFragment
import cn.lxw.business.baselibrary.widget.BannerImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*

/**
 ***************************************************
 * 猿代码： Lxw
 * Email： China2021@126.com
 * 时间轴： 2018年08月10日 16:05
 ***************************************************
 * 备注：
 * 功能描述：
 */
class HomeFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
    }

    private fun initBanner() {
        mHomeBannerHb.setImageLoader(BannerImageLoader())
        mHomeBannerHb.setImages(mutableListOf(IMAGE_1, IMAGE_2, IMAGE_3, IMAGE_4, IMAGE_5, IMAGE_6, IMAGE_7, IMAGE_8))
        mHomeBannerHb.setBannerAnimation(Transformer.Accordion)
        mHomeBannerHb.setDelayTime(2500)
        mHomeBannerHb.setIndicatorGravity(BannerConfig.RIGHT)
        mHomeBannerHb.start()
    }
}