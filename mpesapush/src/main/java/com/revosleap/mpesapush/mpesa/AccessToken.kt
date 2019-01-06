package com.revosleap.mpesapush.mpesa

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AccessToken(
    @field:SerializedName("access_token")
    @field:Expose
    var accessToken: String,
    @field:SerializedName("expires_in")
    @field:Expose
    private val expiresIn: String
)
