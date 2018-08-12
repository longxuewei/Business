package com.lxw.business.messagecenter.weight

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.ViewFlipper
import com.lxw.business.messagecenter.R
import org.jetbrains.anko.dimen
import org.jetbrains.anko.find
import org.jetbrains.anko.px2sp

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年08月12日 22:18
 * *******************************
 * 备注：
 * 功能描述：
 */
class NewsFlipperView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val mFlipperView: ViewFlipper

    init {
        val view = View.inflate(context, R.layout.layout_news_flipper, null)
        mFlipperView = view.find(R.id.mFlipperView)
        mFlipperView.setInAnimation(context, R.anim.news_bottom_in)
        mFlipperView.setOutAnimation(context, R.anim.news_bottom_out)
        addView(view)
    }


    /**
     * 构建一个TextView用于添加德奥FlipperView 里面
     * [text] : 显示的文字
     */
    private fun buildNewsView(text: String): View {
        val textView = TextView(context)
        textView.text = text
        textView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        textView.textSize = px2sp(dimen(R.dimen.text_small_size))
        return textView
    }


    /**
     * 设置一组文字通知:
     * [texts] : 文字集
     */
    fun setData(texts: Array<String>) {
        texts.forEach {
            mFlipperView.addView(buildNewsView(it))
        }
        mFlipperView.startFlipping()
    }


}