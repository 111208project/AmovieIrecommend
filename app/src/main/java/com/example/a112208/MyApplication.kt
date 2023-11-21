package com.example.a112208

import android.app.Application
import android.util.Log
import com.example.a112208.api.ApiService
import com.example.a112208.api.LoginRequest
import com.example.a112208.api.Register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {

    private val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://140.131.114.157:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

    override fun onCreate() {
        super.onCreate()

        // 在應用啟動時連結到 Flask 服務並初始化資料庫
        initDatabase()
    }

    private fun initDatabase() {
        // 在這裡初始化資料庫的邏輯，包括創建表和插入初始數據
        val username = "123"
        val password = "123"
        val email ="123"
        // 創建用戶數據
        val request = Register(username, password,email)
        apiService.registerUser(request).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("MyApplication", "User registered successfully.")
                } else {
                    Log.e("MyApplication", "Failed to register user.")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("MyApplication", "Network error: ${t.message}")
            }
        })
    }
}