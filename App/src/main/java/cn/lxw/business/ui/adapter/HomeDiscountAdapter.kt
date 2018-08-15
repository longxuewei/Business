package cn.lxw.business.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.lxw.business.R
import cn.lxw.business.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.base.utils.GlideUtils
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*


/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月13日 20:18
 * *******************************
 * 备注：折扣适配器
 * 功能描述：
 */
class HomeDiscountAdapter(context: Context) : BaseRecyclerViewAdapter<String, HomeDiscountAdapter.HomeDiscountViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeDiscountViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_home_discount_item, parent, false)
        return HomeDiscountViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeDiscountViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        GlideUtils.loadUrlImage(mContext, dataList[position], holder.itemView.mGoodsIconIv)
        holder.itemView.mDiscountAfterTv.text = "$500"
        holder.itemView.mDiscountBeforeTv.text = "$5000"
    }

    class HomeDiscountViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.mDiscountBeforeTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            view.mDiscountBeforeTv.paint.isAntiAlias = true
        }
    }
}