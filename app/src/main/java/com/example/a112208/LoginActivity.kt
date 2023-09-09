package com.example.a112208

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import java.io.IOException
import android.util.Log

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url("http://140.131.114.157:5000") // 請更換為實際的URL
            .build()

        val btnLogin = findViewById<Button>(R.id.btnlogin)
        val etUsername = findViewById<EditText>(R.id.txtusername)
        val etPassword = findViewById<EditText>(R.id.txtpassword)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // 先驗證帳號和密碼是否正確
            if (isValidCredentials(username, password)) {
                // 執行網絡請求
                okHttpClient.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        runOnUiThread {
                            Toast.makeText(this@LoginActivity, "網路連接失敗", Toast.LENGTH_LONG).show()
                        }
                        Log.d("NetworkError", "網路連接失敗: ${e.message}")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        runOnUiThread {
                            Toast.makeText(this@LoginActivity, "網路連接成功", Toast.LENGTH_LONG).show()

                            // 登入成功，跳轉到ChooseActivity
                            val intent = Intent(this@LoginActivity, ChooseActivity::class.java)
                            startActivity(intent)
                        }
                    }
                })
            } else {
                // 帳號或密碼不正確，顯示錯誤消息
                Toast.makeText(this@LoginActivity, "請確認使用者名稱或密碼", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        // 在這裡實現帳號和密碼的驗證邏輯
        // 返回true表示帳號和密碼正確，返回false表示帳號和密碼不正確
        return (username == "user" || username == "admin") && password == "123456"
    }
}
