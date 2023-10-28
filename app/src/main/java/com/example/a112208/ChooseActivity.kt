package com.example.a112208

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a112208.api.ApiService
import com.example.a112208.api.InterestRequest
import com.example.a112208.api.RecommendedContentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChooseActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://140.131.114.157:5000/") // 請確保這裡是正確的 API 端點
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        val btnChoose = findViewById<Button>(R.id.btnChoose)
        val checkbox1 = findViewById<CheckBox>(R.id.checkbox1)
        val checkbox2 = findViewById<CheckBox>(R.id.checkbox2)
        val checkbox3 = findViewById<CheckBox>(R.id.checkbox3)
        val checkbox4 = findViewById<CheckBox>(R.id.checkbox4)
        val checkbox5 = findViewById<CheckBox>(R.id.checkbox5)
        val checkbox6 = findViewById<CheckBox>(R.id.checkbox6)

        val imageView1 = findViewById<ImageView>(R.id.my_image_view)
        val imageView2 = findViewById<ImageView>(R.id.ImageView2)
        val imageView3 = findViewById<ImageView>(R.id.ImageView3)
        val imageView4 = findViewById<ImageView>(R.id.ImageView4)
        val imageView5 = findViewById<ImageView>(R.id.ImageView5)
        val imageView6 = findViewById<ImageView>(R.id.ImageView6)
        // 為圖片增加點擊監聽
        imageView1.setOnClickListener(View.OnClickListener {
            // 切換選中狀態
            checkbox1.isChecked = !checkbox1.isChecked
        })
        imageView2.setOnClickListener(View.OnClickListener {
            checkbox2.isChecked = !checkbox2.isChecked
        })
        imageView3.setOnClickListener(View.OnClickListener {
            checkbox3.isChecked = !checkbox3.isChecked
        })
        imageView4.setOnClickListener(View.OnClickListener {
            checkbox4.isChecked = !checkbox4.isChecked
        })
        imageView5.setOnClickListener(View.OnClickListener {
            checkbox5.isChecked = !checkbox5.isChecked
        })
        imageView6.setOnClickListener(View.OnClickListener {
            checkbox6.isChecked = !checkbox6.isChecked
        })

        btnChoose.setOnClickListener {
            val interests = mutableListOf<String>()

            if (checkbox1.isChecked) {
                interests.add("科幻")
            }
            if (checkbox2.isChecked) {
                interests.add("動作")
            }
            if (checkbox3.isChecked) {
                interests.add("懸疑")
            }
            if (checkbox4.isChecked) {
                interests.add("愛情")
            }
            if (checkbox5.isChecked) {
                interests.add("喜劇")
            }
            if (checkbox6.isChecked) {
                interests.add("恐怖")
            }

            if (interests.isNotEmpty()) {
                sendInterestsToServer(interests)
            } else {
                Toast.makeText(this@ChooseActivity, "請選擇至少一個興趣", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendInterestsToServer(interests: List<String>) {
        val request = InterestRequest(interests)
        apiService.sendInterests(request).enqueue(object : Callback<RecommendedContentResponse> {
            override fun onResponse(call: Call<RecommendedContentResponse>, response: Response<RecommendedContentResponse>) {
                if (response.isSuccessful) {
                    val recommendedContent = response.body()?.recommendedContent

                    val intent = Intent(this@ChooseActivity, MainActivity::class.java)
                    intent.putStringArrayListExtra("recommendedContent", ArrayList(recommendedContent))
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@ChooseActivity, "伺服器回應失敗", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RecommendedContentResponse>, t: Throwable) {
                Toast.makeText(this@ChooseActivity, "網路錯誤", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
