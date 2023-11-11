package com.example.a112208

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException
import android.util.Log
import androidx.activity.viewModels
import com.example.a112208.api.ApiService
import com.example.a112208.api.LoginRequest

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.ResponseBody
import androidx.lifecycle.ViewModel



class LoginActivity : AppCompatActivity() {
    //初始化viewModels
    private val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://140.131.114.157:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnlogin)
        val btnForget = findViewById<Button>(R.id.btnforget)
        val etUsername = findViewById<EditText>(R.id.txtusername)
        val etPassword = findViewById<EditText>(R.id.txtpassword)
        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val editor = sharedPreferences.edit()
            editor.putString("username",username)
            editor.apply()


            val request = LoginRequest(username, password)
            apiService.login(request).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        // 登入成功處理邏輯
                        val intent = Intent(this@LoginActivity, ChooseActivity::class.java)
                        startActivity(intent)
                        finish() // 結束 LoginActivity，避免返回到登入畫面
                    } else {
                        // 登入失敗處理邏輯
                        Toast.makeText(this@LoginActivity, "登入失敗", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // 網路錯誤處理邏輯
                    Toast.makeText(this@LoginActivity, "網路錯誤", Toast.LENGTH_SHORT).show()
                }
            })

        }
        btnForget.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}

