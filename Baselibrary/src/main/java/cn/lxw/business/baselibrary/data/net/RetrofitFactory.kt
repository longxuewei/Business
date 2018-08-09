package cn.lxw.business.baselibrary.data.net

import cn.lxw.business.baselibrary.common.BaseConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * *******************************
 * 猿代码: Lxw
 * Email: China2021@126.com
 * 时间轴：2018年06月11日 下午9:48
 * *******************************
 * 备注：Retrofit的封装：
 * 1. 采用单例模式封装了Retrofit。
 * 2. 在构造方法中初始化了Retrofit和OkhttpClient。并设置了相关的参数。
 * 3. 使用了日志拦截器，和添加了请求头。
 * 4. 对外提供了Retrofit的接口创建方法[create]
 * 功能描述：
 */
class RetrofitFactory private constructor() {

    private val retrofit: Retrofit


    //延迟加载单例实体
    companion object {
        val instance by lazy { RetrofitFactory() }
    }


    init {
        /** 初始化 Retrofit */
        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVICE_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClient())
                .build()
    }


    /**
     * 初始化Okhttp客户端
     */
    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(initInterceptor())
                .addInterceptor(initBaseInterceptor())
                .build()
    }


    /**
     * 初始化日志拦截器.拦截级别为body
     * [Interceptor]：拦截器
     */
    private fun initInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }


    /**
     * 构建基础的拦截器，像一般的token，和charset 这类最基础的请求头都直接添加，不需要每次去写。
     * [Interceptor]：基础拦截器
     */
    private fun initBaseInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "utf-8")
                    .addHeader("token", if (BaseConstant.KEY_SP_TOKEN.isEmpty()) "" else BaseConstant.KEY_SP_TOKEN)
                    .build()
            it.proceed(request)
        }
    }


    /**
     * 创建service接口
     * [service]：具体的接口
     */
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}