package cn.lxw.business.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.lxw.business.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import cn.lxw.business.goods.R
import cn.lxw.business.goods.data.protocol.Category
import kotlinx.android.synthetic.main.layout_top_category_item.view.*

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018-8-14 20:20:17
 * *******************************
 * 备注：一级分类
 * 功能描述：
 */
class TopCategoryAdapter(context: Context) : BaseRecyclerViewAdapter<Category, TopCategoryAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_top_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //分类名称
        holder.itemView.mTopCategoryNameTv.text = model.categoryName
        //是否被选中
        holder.itemView.mTopCategoryNameTv.isSelected = model.isSelected

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
