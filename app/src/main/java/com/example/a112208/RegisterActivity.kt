package com.example.a112208

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a112208.api.ApiClient
import com.example.a112208.api.ApiService
import com.example.a112208.api.LoginRequest
import com.example.a112208.api.Register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnReturn = findViewById<Button>(R.id.btnReturn)
        val username = findViewById<EditText>(R.id.reusername)
        val password = findViewById<EditText>(R.id.repassword)
        val email = findViewById<EditText>(R.id.email)
        btnSubmit.setOnClickListener {
            val usernameText = username.text.toString()
            val passwordText = password.text.toString()
            val email=email.text.toString()
            val apiService = ApiClient.getApiClient().create(ApiService::class.java)

            val request = Register(usernameText, passwordText,email)
            if(usernameText==""){
                Toast.makeText(this@RegisterActivity, "帳號不能為空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(passwordText==""){
                Toast.makeText(this@RegisterActivity, "密碼不能為空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(email==""){
                Toast.makeText(this@RegisterActivity, "信箱不能為空", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            apiService.registerUser(request).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // 註冊成功處理邏輯
                        Toast.makeText(this@RegisterActivity, "註冊成功", Toast.LENGTH_SHORT).show()
                        finish() // 完成註冊，返回上一個畫面
                    } else {
                        if (response.code() == 400) {
                            // 伺服器返回 400 錯誤，可能是用戶名已存在
                            val errorMessage = "帳號已存在"
                            Toast.makeText(this@RegisterActivity, "註冊失敗：$errorMessage", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // 網路錯誤處理邏輯
                    Toast.makeText(this@RegisterActivity, "網路錯誤", Toast.LENGTH_SHORT).show()
                }
            })
        }
        btnReturn.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
