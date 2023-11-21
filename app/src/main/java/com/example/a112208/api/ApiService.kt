package com.example.a112208.api

import com.example.a112208.data.Movie
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

data class LoginRequest(val username: String, val password: String)
data class InterestRequest(val interests: List<String>, val username: String)
data class InterestResponse(val interests: List<String>, val firstLogin: Boolean)
data class RecommendedContentRequest(val username: String)

data class RecommendedContentResponse(val recommendedContent: List<String>)

//顯示電影畫面
data class SortedMovie(val movieName: String, val imageUrl: String)
data class SortedMovieListRequest(val username: String)

data class SortedMovieListResponse(val movies: List<Movie>)



//



interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<InterestResponse>

    @POST("register")
    fun registerUser(@Body request: LoginRequest): Call<Void>

    @POST("send-interests")
    fun sendInterests(@Body request: InterestRequest, @Query("username") username: String): Call<Void>


    @POST("get-user-interests")
    fun getUserInterests(@Query("username") username: String): Call<InterestResponse>

    @POST("get-sorted-movie-list")
    fun getSortedMovieList(@Body request: SortedMovieListRequest): Call<ResponseBody>

    @GET("movies") // 這裡應該是你實際的 API 端點
    fun getMovies(): Call<List<Movie>>

}
