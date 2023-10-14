package com.example.a112208

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.a112208.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity(){
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
        val Username = findViewById<EditText>(R.id.username)
        val Password = findViewById<EditText>(R.id.password)
        val Email = findViewById<EditText>(R.id.email)




        btnSubmit.setOnClickListener{

        }


    }
}