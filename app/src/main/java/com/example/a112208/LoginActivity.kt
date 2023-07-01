package com.example.a112208

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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