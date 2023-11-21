package com.example.a112208.api

import android.provider.ContactsContract.CommonDataKinds.Email
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

data class LoginRequest(val username: String, val password: String)

data class Register(val username: String, val password: String, val email: String)
data class ChangePassword(val username: String, val newpassword: String)
data class InterestRequest(val interests: List<String>, val username: String)
data class InterestResponse(val interests: List<String>, val firstLogin: Boolean)
data class RecommendedContentRequest(val username: String)

data class RecommendedContentResponse(val recommendedContent: List<String>)

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<InterestResponse>

    @POST("register")
    fun registerUser(@Body request: Register): Call<Void>

    @POST("password")
    fun changePassword(@Body request: ChangePassword): Call<Void>
    @POST("send-interests")
    fun sendInterests(@Body request: InterestRequest, @Query("username") username: String): Call<Void>


    @POST("get-user-interests")
    fun getUserInterests(@Query("username") username: String): Call<InterestResponse>

    @POST("get-user-interests")
    fun getUserInterests(@Body request: RecommendedContentRequest): Call<InterestResponse>
}
