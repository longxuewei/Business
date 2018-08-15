package cn.lxw.business.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ext.loadUrl
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/**
 ***************************************************
 * 猿代码： Lxw
 * Email： China2021@126.com
 * 时间轴： 2018-8-13 20:42:56
 ***************************************************
 * 备注：主页画廊Adapter
 * 功能描述：
 */
class TopicAdapter(private val context: Context, private val list: List<String>) : PagerAdapter() {

    override fun destroyItem(parent: ViewGroup, paramInt: Int, paramObject: Any) {
        parent.removeView(paramObject as View)
    }

    override fun getCount(): Int {
        return this.list.size
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val rooView = LayoutInflater.from(this.context).inflate(R.layout.layout_topic_item, null)
        rooView.mTopicIv.loadUrl(list[position])
        parent.addView(rooView)
        return rooView
    }

    override fun isViewFromObject(paramView: View, paramObject: Any): Boolean {
        return paramView === paramObject
    }
}
