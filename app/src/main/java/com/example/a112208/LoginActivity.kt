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

        // 建立一個 OkHttpClient 實例來處理網絡請求
        val okHttpClient = OkHttpClient()

        // 建立一個指向特定 URL 的請求
        val request = Request.Builder()
            .url("http://140.131.114.157:5000") // 請替換為實際的 URL
            .build()

        // 初始化用戶界面元素
        val btnLogin = findViewById<Button>(R.id.btnlogin)
        val etUsername = findViewById<EditText>(R.id.txtusername)
        val etPassword = findViewById<EditText>(R.id.txtpassword)

        // 為登錄按鈕設置點擊監聽器
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // 驗證用戶名和密碼
            if (isValidCredentials(username, password)) {
                // 在後台執行網絡請求
                okHttpClient.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        // 在主線程上處理網絡失敗，並顯示一個 Toast 消息
                        runOnUiThread {
                            Toast.makeText(this@LoginActivity, "網路連接失敗", Toast.LENGTH_LONG).show()
                        }
                        // 記錄錯誤消息以便調試
                        Log.d("NetworkError", "網路連接失敗: ${e.message}")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        // 在主線程上處理成功的網絡響應，並顯示一個 Toast 消息
                        runOnUiThread {
                            Toast.makeText(this@LoginActivity, "網路連接成功", Toast.LENGTH_LONG).show()

                            // 如果登錄成功，跳轉到 ChooseActivity
                            val intent = Intent(this@LoginActivity, ChooseActivity::class.java)
                            startActivity(intent)
                        }
                    }
                })
            } else {
                // 如果用戶名或密碼不正確，顯示錯誤消息
                Toast.makeText(this@LoginActivity, "請確認使用者名稱或密碼", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 函數用於驗證用戶名和密碼
    private fun isValidCredentials(username: String, password: String): Boolean {
        // 實現用戶名和密碼的驗證邏輯
        // 如果用戶名和密碼正確，返回 true；否則返回 false
        return (username == "user" || username == "admin") && password == "123456"
    }
}
