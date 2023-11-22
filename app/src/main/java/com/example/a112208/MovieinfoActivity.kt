package com.example.a112208

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MovieinfoActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_info)
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)
        // 設定初始星數為3.5
        val initialRating = 5f
        ratingBar.rating = initialRating

        val btnscore = findViewById<Button>(R.id.score)
        btnscore.setOnClickListener {
            val intent = Intent(this@MovieinfoActivity, ScoreActivity::class.java)
            startActivity(intent)
        }
            val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            val intent = Intent(this@MovieinfoActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }

}