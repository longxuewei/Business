package cn.lxw.business.goods.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder
import cn.bingoogolapple.refreshlayout.BGARefreshLayout
import cn.lxw.business.baselibrary.ext.startLoading
import cn.lxw.business.baselibrary.ui.activity.BaseMvpActivity
import cn.lxw.business.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import cn.lxw.business.goods.R
import cn.lxw.business.goods.common.GoodsConstant
import cn.lxw.business.goods.common.GoodsConstant.Companion.KEY_GOODS_CATEGORY_ID
import cn.lxw.business.goods.data.protocol.Goods
import cn.lxw.business.goods.injection.component.DaggerGoodsComponent
import cn.lxw.business.goods.injection.module.GoodsModule
import cn.lxw.business.goods.presenter.GoodsPresenter
import cn.lxw.business.goods.presenter.view.GoodsListView
import cn.lxw.business.goods.ui.adapter.GoodsAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_goods.*
import org.jetbrains.anko.startActivity

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月16日 21:33
 * *******************************
 * 备注：
 * 功能描述：
 */
class GoodsListActivity : BaseMvpActivity<GoodsPresenter>(), GoodsListView, BGARefreshLayout.BGARefreshLayoutDelegate {

    private lateinit var adapter: GoodsAdapter
    private var currentPage = 1
    private var maxPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        initRefreshLayout()
        loadData()
    }

    private fun initRefreshLayout() {
        mRefreshLayout.setRefreshViewHolder(BGANormalRefreshViewHolder(this, true).apply {
            this.setLoadMoreBackgroundColorRes(R.color.common_bg)
            this.setRefreshViewBackgroundColorRes(R.color.common_bg)
        })
        mRefreshLayout.setDelegate(this)
    }

    private fun initView() {
        adapter = GoodsAdapter(this)
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsRv.adapter = adapter
        adapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Goods> {
            override fun onItemClick(item: Goods, position: Int) {
                startActivity<GoodsDetailActivity>(GoodsConstant.KEY_GOODS_ID to item.id)
            }
        })
    }

    private fun loadData() {
        //关键字
        mMultiStateView.startLoading()
        if (intent.getIntExtra(GoodsConstant.KEY_SEARCH_GOODS_TYPE, 0) != 0) {
            presenter.getGoodsListByKeyword(intent.getStringExtra(GoodsConstant.KEY_GOODS_KEYWORD), currentPage)
        } else {
            presenter.getGoodsList(intent.getIntExtra(KEY_GOODS_CATEGORY_ID, 1), currentPage)
        }
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        presenter.mView = this
    }

    override fun onGoodsListResult(result: MutableList<Goods>?) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()

        result?.let {
            maxPage = it[0].maxPage
            if (currentPage != 1) {
                adapter.dataList.addAll(result)
                adapter.notifyDataSetChanged()
            } else {
                adapter.setData(it)
            }
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }
        if (result == null || result.isEmpty()) {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

    }

    override fun onBGARefreshLayoutBeginLoadingMore(refreshLayout: BGARefreshLayout?): Boolean {
        return if (currentPage < maxPage) {
            currentPage++
            loadData()
            true
        } else {
            false
        }
    }

    override fun onError(msg: String) {
        super.onError(msg)
        mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR
    }

    override fun onBGARefreshLayoutBeginRefreshing(refreshLayout: BGARefreshLayout?) {
        currentPage = 1
        loadData()
    }

}