package cn.lxw.business.baselibrary.widget

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import cn.lxw.business.baselibrary.R
import org.jetbrains.anko.find

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年07月11日 下午10:04
 * *******************************
 * 备注：实现最基本的加载框
 * 功能描述：
 */
class ProgressLoading(context: Context, style: Int) : Dialog(context, style) {

    companion object {
        lateinit var mDialog: ProgressLoading
        private var anim: AnimationDrawable? = null

        fun create(context: Context): ProgressLoading {
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)

            val attr = mDialog.window.attributes
            attr.dimAmount = 0.2f

            mDialog.window.attributes = attr

            val ivDialog = mDialog.find<ImageView>(R.id.ivDialog)
            anim = ivDialog.background as AnimationDrawable

            return mDialog

        }
    }

    override fun show() {
        super.show()
        anim?.start()
    }

    override fun dismiss() {
        super.dismiss()
        anim?.stop()
    }
}