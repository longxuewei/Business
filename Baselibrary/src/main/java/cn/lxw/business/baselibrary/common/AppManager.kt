package cn.lxw.business.baselibrary.common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年07月02日 下午10:15
 * *******************************
 * 备注：
 * 功能描述：
 */
class AppManager private constructor() {
    private val activitys: Stack<Activity> = Stack()


    fun addActivity(activity: Activity) {
        activitys.add(activity)
    }


    fun finishActivity(activity: Activity) {
        activity.finish()
        activitys.remove(activity)
    }

    fun currentActivity(): Activity = activitys.lastElement()


    fun finishAllActivity() {
        activitys.let {
            it.forEach {
                it.finish()
            }
            it.clear()
        }
    }


    @SuppressLint("MissingPermission")
    fun exit(context: Context) {
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }


    companion object {
        val instance: AppManager by lazy { AppManager() }
    }
}