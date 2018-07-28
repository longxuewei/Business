package cn.lxw.business.baselibrary.rx

import cn.lxw.business.baselibrary.common.ResultCode
import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年07月01日 上午9:48
 * *******************************
 * 备注：该类用于定义一个，正常返回服务器数据的Observable.
 * 功能描述：
 */
class BaseFunc<T> : Function<BaseResponse<T>, Observable<T>> {
    override fun apply(t: BaseResponse<T>): Observable<T> {
        return when (t.status) {
            ResultCode.SUCCESS -> Observable.just(t.data)
            else -> Observable.error(BaseException(t.status, t.message))
        }
    }

}