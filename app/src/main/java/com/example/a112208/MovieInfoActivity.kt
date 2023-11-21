package com.example.a112208

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.a112208.databinding.ActivityMovieInfoBinding

class MovieInfoActivity : AppCompatActivity() {

    companion object {
        private const val MOVIE_NAME_KEY = "movie_name"
        private const val MOVIE_IMAGE_URL_KEY = "movie_image_url"

        fun newIntent(context: Context, movieName: String, movieImageUrl: String): Intent {
            val intent = Intent(context, MovieInfoActivity::class.java)
            intent.putExtra(MOVIE_NAME_KEY, movieName)
            intent.putExtra(MOVIE_IMAGE_URL_KEY, movieImageUrl)
            return intent
        }
    }

    private lateinit var binding: ActivityMovieInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 获取传递的电影信息
        val movieName = intent.getStringExtra(MOVIE_NAME_KEY)
        val movieImageUrl = intent.getStringExtra(MOVIE_IMAGE_URL_KEY)

        // 设置电影信息
        binding.tvMovieName.text = movieName
        Glide.with(this).load(movieImageUrl).into(binding.ivMovieImage)
    }
}
