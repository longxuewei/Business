package cn.lxw.business.goods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.lxw.business.baselibrary.ext.loadUrl
import cn.lxw.business.baselibrary.ui.activity.BaseFragment
import cn.lxw.business.goods.R
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import cn.lxw.business.goods.event.GoodsDetailImageEvent
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月13日 22:43
 * *******************************
 * 备注：
 * 功能描述：
 */
class GoodsDetailTabTwoFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_goods_detail_tab_two, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    /**
     * 初始化监听.
     */
    private fun initObserve() {
        Bus.observe<GoodsDetailImageEvent>()
                .subscribe { t: GoodsDetailImageEvent ->
                    run {
                        mGoodsDetailOneIv.loadUrl(t.imgOne)
                        mGoodsDetailTwoIv.loadUrl(t.imgTwo)
                    }
                }
                .registerInBus(this)

    }


    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}