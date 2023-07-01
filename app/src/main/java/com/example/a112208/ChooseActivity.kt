package com.example.a112208

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ChooseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)
        var btnChoose = findViewById<Button>(R.id.btnChoose)

        btnChoose.setOnClickListener{
            var intent= Intent (this, MsinActivity::class.java)
            startActivity(intent)
        }
    }
}