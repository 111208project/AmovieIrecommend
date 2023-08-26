package com.example.a112208

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.*
import java.io.IOException
import android.util.Log

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*網路連線請求(連上了)--------------------------------------------------------*/
        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url("http://140.131.114.157:5000")
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "網路連接失敗", Toast.LENGTH_LONG).show()
                }
                Log.d("NetworkError", "網路連接失敗: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                runOnUiThread {
                    val textView = findViewById<TextView>(R.id.textView)
                    textView.text = responseBody
                }
            }
        })
        /*網路連線請求(連上了)--------------------------------------------------------*/

        val btnLogin = findViewById<Button>(R.id.btnlogin)
        val etUsername = findViewById<EditText>(R.id.txtusername)
        val etPassword = findViewById<EditText>(R.id.txtpassword)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // 这里您需要根据您的需求替换成真实的用户名和密码
            if (username == "user" && password == "123456") {
                Toast.makeText(this, "登入成功!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, ChooseActivity::class.java)
                startActivity(intent)
            } else if(username == "admin" && password == "123456") {
                Toast.makeText(this, "登入成功!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, ChooseActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "請確認使用者名稱或密碼", Toast.LENGTH_SHORT).show()
            }
        }
    }
}