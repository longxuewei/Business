package cn.lxw.business.baselibrary.rx

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年07月01日 上午9:54
 * *******************************
 * 备注： 定义通用异常
 * 功能描述：
 */
class BaseException(val status:Int,val msg:String): Throwable() {
}