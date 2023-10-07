package com.example.a112208

import android.app.Application
import android.util.Log
import com.example.a112208.api.ApiService
import com.example.a112208.api.LoginRequest
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

        // 在应用启动时连接到 Flask 服务并初始化数据库
        initDatabase()
    }

    private fun initDatabase() {
        // 在这里处理初始化数据库的逻辑，包括创建表和插入初始数据
        val username = "123"
        val password = "123"

        // 创建用户数据
        val request = LoginRequest(username, password)
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