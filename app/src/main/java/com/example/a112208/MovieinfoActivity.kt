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

        // 獲取 RatingBar 實例
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)

        // 設定初始星數為5
        val initialRating = 5f
        ratingBar.rating = initialRating

        // 獲取評分按鈕實例
        val btnScore = findViewById<Button>(R.id.score)

        // 設定評分按鈕的點擊監聽器
        btnScore.setOnClickListener {
            // 點擊評分按鈕，跳轉到 ScoreActivity
            val intent = Intent(this@MovieinfoActivity, ScoreActivity::class.java)
            startActivity(intent)
        }
    }
}
