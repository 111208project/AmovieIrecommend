package com.example.a112208

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request

class Register : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_register)
        // 建立一個 OkHttpClient 實例來處理網絡請求
        val okHttpClient = OkHttpClient()

        // 建立一個指向特定 URL 的請求
        val request = Request.Builder()
            .url("http://140.131.114.157:5000") // 請替換為實際的 URL
            .build()

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener{

        }


    }
}