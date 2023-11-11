package com.example.a112208

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a112208.R
import com.example.a112208.api.ApiClient
import com.example.a112208.api.ApiService
import com.example.a112208.api.InterestRequest
import com.example.a112208.api.InterestResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChooseActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService
    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        apiService = ApiClient.getApiClient().create(ApiService::class.java)

        // 從SharedPreferences中讀取用戶名
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        username = sharedPreferences.getString("username", "") ?: ""

        val btnChoose = findViewById<Button>(R.id.btnChoose)
        val checkbox1 = findViewById<CheckBox>(R.id.checkbox1)
        val checkbox2 = findViewById<CheckBox>(R.id.checkbox2)
        val checkbox3 = findViewById<CheckBox>(R.id.checkbox3)
        val checkbox4 = findViewById<CheckBox>(R.id.checkbox4)
        val checkbox5 = findViewById<CheckBox>(R.id.checkbox5)
        val checkbox6 = findViewById<CheckBox>(R.id.checkbox6)

        btnChoose.setOnClickListener {
            val interests = mutableListOf<String>()

            if (checkbox1.isChecked) {
                interests.add("1")
            }
            if (checkbox2.isChecked) {
                interests.add("2")
            }
            if (checkbox3.isChecked) {
                interests.add("3")
            }
            if (checkbox4.isChecked) {
                interests.add("4")
            }
            if (checkbox5.isChecked) {
                interests.add("5")
            }
            if (checkbox6.isChecked) {
                interests.add("6")
            }

            if (interests.isNotEmpty()) {
                sendInterestsToServer(interests)
            } else {
                Toast.makeText(this@ChooseActivity, "請選擇至少一個興趣", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendInterestsToServer(interests: List<String>) {
        if (username.isNullOrEmpty()) {
            // 处理没有有效用户名的情况，可能需要重新登录或其他操作
            return
        }
        val request = InterestRequest(interests, username)
        apiService.sendInterests(request, username).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // 兴趣保存成功
                    getUserInterestsFromServer()
                } else {
                    Log.e("ChooseActivity", "Interest save failed. Response: ${response.code()}")
                    Toast.makeText(this@ChooseActivity, "兴趣保存失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("ChooseActivity", "Network error: ${t.message}")
                Toast.makeText(this@ChooseActivity, "网络错误", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun getUserInterestsFromServer() {
        apiService.getUserInterests(username).enqueue(object : Callback<InterestResponse> {
            override fun onResponse(call: Call<InterestResponse>, response: Response<InterestResponse>) {
                if (response.isSuccessful) {
                    val interests = response.body()?.interests

                    if (interests != null) {
                        val intent = Intent(this@ChooseActivity, MainActivity::class.java)
                        intent.putStringArrayListExtra("userInterests", ArrayList(interests))
                        intent.putStringArrayListExtra(
                            "recommendedContent",
                            intent.getStringArrayListExtra("recommendedContent")
                        )
                        startActivity(intent)
                        finish()
                    } else {
                        // 興趣為 null，直接跳轉到 MainActivity
                        goToMainActivity()
                    }
                } else {
                    // 獲取興趣失敗，跳轉到 MainActivity
                    goToMainActivity()
                }
            }

            override fun onFailure(call: Call<InterestResponse>, t: Throwable) {
                // 網路錯誤，跳轉到 MainActivity
                goToMainActivity()
            }
        })
    }

    private fun goToMainActivity() {
        val intent = Intent(this@ChooseActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
