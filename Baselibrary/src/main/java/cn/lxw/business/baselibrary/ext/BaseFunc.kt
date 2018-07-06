package cn.lxw.business.baselibrary.ext

import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年07月01日 上午9:48
 * *******************************
 * 备注：定义用于返回结果为“数据”值的Fun。比方说请求返回回来的是一个列表，或者对象
 * 功能描述：
 */
class BaseFunc<T> : Function<BaseResponse<T>, Observable<T>> {
    override fun apply(t: BaseResponse<T>): Observable<T> {
        return Observable.just(t.data)
    }
}