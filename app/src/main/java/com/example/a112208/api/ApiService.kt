package com.example.a112208.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val username: String, val password: String)

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<ResponseBody>
    @POST("register") // 这里的 "register" 是你的 Flask API 提供的注册用户的端点
    fun registerUser(@Body request: LoginRequest): Call<Void>
}