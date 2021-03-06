package cn.lxw.business.baselibrary.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import cn.lxw.business.baselibrary.R
import cn.lxw.business.baselibrary.ext.onClick
import kotlinx.android.synthetic.main.layout_header_bar.view.*
import org.jetbrains.anko.find

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年07月06日 上午8:05
 * *******************************
 * 备注：统一封装HeadBar
 * 功能描述：
 */
class HeadBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var isShowBack: Boolean = true
    var titleText: String? = null
    var rightText: String? = null

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.HeadBar)
        isShowBack = typeArray.getBoolean(R.styleable.HeadBar_isShowBack, true)
        titleText = typeArray.getString(R.styleable.HeadBar_titleText)
        rightText = typeArray.getString(R.styleable.HeadBar_rightText)

        initView()
        typeArray.recycle()
    }

    private fun initView() {
        View.inflate(context, R.layout.layout_header_bar, this)

        mLeftIv.visibility = if (isShowBack) View.VISIBLE else View.GONE
        titleText?.let {
            mTitleTv.text = it
        }
        rightText?.let {
            mRightTv.text = it
        }


        //默认finish Activity
        mLeftIv.onClick {
            if (context is Activity) {
                (context as Activity).finish()
            }
        }
    }


    /**
     * 获取右边的TextView
     */
    fun getRightTextView(): TextView {
        return find(R.id.mRightTv)
    }

}