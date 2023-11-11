package com.example.a112208

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a112208.ChooseActivity
import com.example.a112208.R
import com.example.a112208.RegisterActivity
import com.example.a112208.api.ApiClient
import com.example.a112208.api.ApiService
import com.example.a112208.api.InterestResponse
import com.example.a112208.api.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        apiService = ApiClient.createService()
        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

        val btnLogin = findViewById<Button>(R.id.btnlogin)
        val btnRegister = findViewById<Button>(R.id.btnregister)
        val etUsername = findViewById<EditText>(R.id.txtusername)
        val etPassword = findViewById<EditText>(R.id.txtpassword)

        btnLogin.setOnClickListener {
            // 清除用戶信息
            clearUserInfo()

            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // 在這裡使用 sharedPreferences 執行相應的操作
            val editor = sharedPreferences.edit()
            editor.putString("username", username)
            // 如果有其他需要保存的信息，也在這裡添加相應的保存操作
            editor.apply()

            val request = LoginRequest(username, password)
            apiService.login(request).enqueue(object : Callback<InterestResponse> {
                override fun onResponse(
                    call: Call<InterestResponse>,
                    response: Response<InterestResponse>
                ) {
                    if (response.isSuccessful) {
                        val interestsJson = response.body()?.interests
                        if (!interestsJson.isNullOrEmpty()) {
                            // 有興趣資料，跳轉到 MainActivity 並傳遞興趣資料
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra("interests", interestsJson.joinToString())
                            startActivity(intent)
                            finish()
                        } else {
                            // 無興趣資料，跳轉到 ChooseActivity
                            val firstLogin = response.body()?.firstLogin ?: false
                            if (firstLogin) {
                                // 第一次登入，需要選擇興趣
                                val intent = Intent(this@LoginActivity, ChooseActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                // 不是第一次登入，直接跳轉到 MainActivity
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    } else {
                        // 无法獲取興趣資料，處理錯誤
                        Toast.makeText(this@LoginActivity, "登入失敗", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<InterestResponse>, t: Throwable) {
                    // 處理網路錯誤
                    Toast.makeText(this@LoginActivity, "網路錯誤", Toast.LENGTH_SHORT).show()
                }
            })
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goToMainActivity(interests: String) {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("interests", interests)
        startActivity(intent)
        finish()
    }
    private fun clearUserInfo() {
        val editor = sharedPreferences.edit()
        editor.remove("username")
        // 如果有其他需要清除的信息，也在這裡添加相應的清除操作
        editor.apply()
    }
}
