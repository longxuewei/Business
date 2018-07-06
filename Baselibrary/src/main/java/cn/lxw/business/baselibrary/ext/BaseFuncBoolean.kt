package cn.lxw.business.baselibrary.ext

import cn.lxw.business.baselibrary.data.protocol.BaseResponse
import cn.lxw.business.baselibrary.rx.BaseException
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年07月01日 上午9:48
 * *******************************
 * 备注：定义用于返回结果为boolean值的
 * 功能描述：
 */
class BaseFuncBoolean<T> : Function<BaseResponse<T>, Observable<Boolean>> {
    override fun apply(t: BaseResponse<T>): Observable<Boolean> {
        return when (t.status) {
            0 -> Observable.just(true)
            else -> Observable.error(BaseException(t.status, t.message))
        }
    }

}