package com.revosleap.mpesapush.mpesa.services


import com.revosleap.mpesapush.mpesa.AccessToken
import com.revosleap.mpesapush.mpesa.STKPush
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface STKPushService {

    @GET("jobs/pending")
    fun tasks(): Call<STKPush>

    @GET("oauth/v1/generate?grant_type=client_credentials")
    fun accessToken(): Call<AccessToken>

    @POST("mpesa/stkpush/v1/processrequest")
    fun sendPush(@Body stkPush: STKPush): Call<STKPush>
}
