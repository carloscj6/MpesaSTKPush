package com.revosleap.mpesapush.mpesa.interceptor


import android.util.Base64
import com.revosleap.mpesapush.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val keys = BuildConfig.CONSUMER_KEY + ":" + BuildConfig.CONSUMER_SECRET
        //Consumer key, consumer secret

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.toByteArray(), Base64.NO_WRAP))
            .build()
        return chain.proceed(request)
    }
}