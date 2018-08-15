package cn.lxw.business.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.lxw.business.baselibrary.ext.setGone
import cn.lxw.business.baselibrary.ext.startLoading
import cn.lxw.business.baselibrary.ui.activity.BaseMvpFragment
import cn.lxw.business.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import cn.lxw.business.goods.R
import cn.lxw.business.goods.data.protocol.Category
import cn.lxw.business.goods.injection.component.DaggerCategoryComponent
import cn.lxw.business.goods.injection.module.CategoryModule
import cn.lxw.business.goods.presenter.CategoryPresenter
import cn.lxw.business.goods.presenter.view.CategoryView
import cn.lxw.business.goods.ui.adapter.SecondCategoryAdapter
import cn.lxw.business.goods.ui.adapter.TopCategoryAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月13日 22:43
 * *******************************
 * 备注：
 * 功能描述：
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    private lateinit var mTopCategoryAdapter: TopCategoryAdapter
    private lateinit var mSecondCategoryAdapter: SecondCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_category, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }


    /**
     * 加载数据
     */
    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }
        presenter.getCategory(parentId)
    }

    private fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(activity)
        mTopCategoryAdapter = TopCategoryAdapter(activity!!)
        mTopCategoryRv.adapter = mTopCategoryAdapter
        mTopCategoryAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                mTopCategoryAdapter.dataList.forEach {
                    it.isSelected = item.id == it.id
                }
                mTopCategoryAdapter.notifyDataSetChanged()
                loadData(item.id)
            }
        })


        mSecondCategoryRv.layoutManager = GridLayoutManager(activity, 3)
        mSecondCategoryAdapter = SecondCategoryAdapter(activity!!)
        mSecondCategoryRv.adapter = mSecondCategoryAdapter
        mSecondCategoryAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                TODO()
            }
        })
    }

//
//    /**
//     * 懒加载
//     * [isVisibleToUser]: 对于用户是否可见
//     */
//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//        super.setUserVisibleHint(isVisibleToUser)
//        if (isVisibleToUser && mTopCategoryAdapter.itemCount == 0 || mSecondCategoryAdapter.itemCount == 0) {
//            loadData()
//        }
//    }

    override fun onGetCategoryResult(result: MutableList<Category>?) {
        result?.let {
            if (it.any { it.parentId == 0 }) {
                it[0].isSelected = true
                mTopCategoryAdapter.setData(it)
                loadData(it[0].id)
            } else {
                mSecondCategoryAdapter.setData(it)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
                setGone(mCategoryTitleTv, mTopCategoryIv, isGone = false)
            }
        }
        if (result == null || result.isEmpty()) {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
            setGone(mCategoryTitleTv, mTopCategoryIv, isGone = true)
        }

    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(activityComponent).categoryModule(CategoryModule()).build().inject(this)
        presenter.mView = this
    }

}