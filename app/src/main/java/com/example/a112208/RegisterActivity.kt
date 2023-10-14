package com.example.a112208

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a112208.api.ApiService
import com.example.a112208.api.LoginRequest
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    private val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://140.131.114.157:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        //val email = findViewById<EditText>(R.id.email)

        btnSubmit.setOnClickListener {
            val usernameText = username.text.toString()
            val passwordText = password.text.toString()
            //val emailText = email.text.toString()

            // 創建一個 LoginRequest 物件，用於傳遞註冊資訊
            val request = LoginRequest(usernameText, passwordText)

            // 使用 Retrofit 2 的 apiService 調用註冊方法
            apiService.registerUser(request).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // 註冊成功處理邏輯
                        Toast.makeText(this@RegisterActivity, "註冊成功", Toast.LENGTH_SHORT).show()
                        finish() // 完成註冊，返回上一個畫面
                    } else {
                        // 註冊失敗處理邏輯
                        Toast.makeText(this@RegisterActivity, "註冊失敗", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // 網路錯誤處理邏輯
                    Toast.makeText(this@RegisterActivity, "網路錯誤", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}