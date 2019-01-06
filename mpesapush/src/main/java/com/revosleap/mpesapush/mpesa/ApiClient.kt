package com.revosleap.mpesapush.mpesa


import com.revosleap.mpesapush.mpesa.interceptor.AccessTokenInterceptor
import com.revosleap.mpesapush.mpesa.interceptor.AuthInterceptor
import com.revosleap.mpesapush.mpesa.services.STKPushService
import com.revosleap.mpesapush.utils.AppConstants.BASE_URL
import com.revosleap.mpesapush.utils.AppConstants.CONNECT_TIMEOUT
import com.revosleap.mpesapush.utils.AppConstants.READ_TIMEOUT
import com.revosleap.mpesapush.utils.AppConstants.WRITE_TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit


class ApiClient {

    private var retrofit: Retrofit? = null
    private var isDebug: Boolean = false
    private var isGetAccessToken: Boolean = false
    private var mAuthToken: String? = null
    private val httpLoggingInterceptor = HttpLoggingInterceptor()

    /**
     * Return the current [Retrofit] instance. If none exists (first call, API key changed),
     * builds a new one.
     *
     *
     * When building, sets the endpoint and a [HttpLoggingInterceptor] which adds the API key as query param.
     */
    private val restAdapter: Retrofit
        get() {

            val builder = Retrofit.Builder()
            builder.baseUrl(BASE_URL)
            builder.addConverterFactory(GsonConverterFactory.create())

            if (isDebug) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }

            val okhttpBuilder = okHttpClient()

            if (isGetAccessToken) {
                okhttpBuilder.addInterceptor(AccessTokenInterceptor())
            }

            if (mAuthToken != null && !mAuthToken!!.isEmpty()) {
                okhttpBuilder.addInterceptor(AuthInterceptor(mAuthToken!!))
            }

            builder.client(okhttpBuilder.build())

            retrofit = builder.build()

            return builder.build()
        }

    /**
     * Set the [Retrofit] log level. This allows one to view network traffic.
     *
     * @param isDebug If true, the log level is set to
     * [HttpLoggingInterceptor.Level.BODY]. Otherwise
     * [HttpLoggingInterceptor.Level.NONE].
     */
    fun setIsDebug(isDebug: Boolean): ApiClient {
        this.isDebug = isDebug
        return this
    }

    /**
     * Helper method used to set the authenication Token
     *
     * @param authToken token from api
     */
    fun setAuthToken(authToken: String): ApiClient {
        mAuthToken = authToken
        return this
    }

    /**
     * Helper method used to determine if get token enpoint has been invoked. This should be called
     * only when requesting of an accessToken
     *
     * @param getAccessToken [Boolean]
     */
    fun setGetAccessToken(getAccessToken: Boolean): ApiClient {
        isGetAccessToken = getAccessToken
        return this
    }

    /**
     * Configure OkHttpClient
     *
     * @return OkHttpClient
     */
    private fun okHttpClient(): OkHttpClient.Builder {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)

        return okHttpClient
    }

    /**
     * Create service instance.
     *
     * @return STKPushService Service.
     */
    fun mpesaService(): STKPushService {
        return restAdapter.create(STKPushService::class.java)
    }

}
