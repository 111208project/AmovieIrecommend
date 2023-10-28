package com.example.a112208.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val username: String, val password: String)
data class InterestRequest(val interests: List<String>)
data class RecommendedContentResponse(val recommendedContent: List<String>)




interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<ResponseBody>
    @POST("register") // 這裡的 "register" 是你的 Flask API 提供的註冊用戶的端點
    fun registerUser(@Body request: LoginRequest): Call<Void>
    @POST("send-interests") // 新增的 API 方法，用於發送興趣選擇
    fun sendInterests(@Body request: InterestRequest): Call<RecommendedContentResponse>
}
