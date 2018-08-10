package cn.lxw.business.baselibrary.widget

import android.content.Context
import android.widget.ImageView
import com.kotlin.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 ***************************************************
 * 猿代码： Lxw
 * Email： China2021@126.com
 * 时间轴： 2018年08月10日 16:15
 ***************************************************
 * 备注：实现Banner的ImageLoader,自定义图片的显示方式
 * 功能描述：
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadUrlImage(context, path.toString(), imageView)
    }
}