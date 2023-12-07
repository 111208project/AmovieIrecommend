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

        // 初始化 Retrofit 服務
        apiService = ApiClient.getApiClient().create(ApiService::class.java)

        // 從SharedPreferences中讀取用戶名
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        username = sharedPreferences.getString("username", "") ?: ""

        // 獲取視圖元素
        val btnChoose = findViewById<Button>(R.id.btnChoose)
        val checkbox1 = findViewById<CheckBox>(R.id.checkbox1)
        val checkbox2 = findViewById<CheckBox>(R.id.checkbox2)
        val checkbox3 = findViewById<CheckBox>(R.id.checkbox3)
        val checkbox4 = findViewById<CheckBox>(R.id.checkbox4)
        val checkbox5 = findViewById<CheckBox>(R.id.checkbox5)
        val checkbox6 = findViewById<CheckBox>(R.id.checkbox6)

        // 設定按鈕點擊監聽器
        btnChoose.setOnClickListener {
            // 創建一個字串列表，用於存儲用戶選擇的興趣
            val interests = mutableListOf<String>()

            // 檢查每個勾選框的狀態，如果被勾選，將相應的興趣編號添加到列表中
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

            // 檢查用戶是否選擇了至少一個興趣
            if (interests.isNotEmpty()) {
                // 向服務器發送用戶選擇的興趣
                sendInterestsToServer(interests)
            } else {
                // 如果未選擇興趣，顯示提示消息
                Toast.makeText(this@ChooseActivity, "請選擇至少一個興趣", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 向服務器發送用戶選擇的興趣
    private fun sendInterestsToServer(interests: List<String>) {
        // 檢查用戶名是否為空
        if (username.isNullOrEmpty()) {
            // 处理没有有效用户名的情况，可能需要重新登录或其他操作
            return
        }

        // 創建一個 InterestRequest 對象
        val request = InterestRequest(interests, username)

        // 使用 Retrofit 向服務器發送興趣請求
        apiService.sendInterests(request, username).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // 興趣保存成功，獲取用戶興趣列表
                    getUserInterestsFromServer()
                } else {
                    // 興趣保存失敗，記錄錯誤信息並顯示提示消息
                    Log.e("ChooseActivity", "Interest save failed. Response: ${response.code()}")
                    Toast.makeText(this@ChooseActivity, "兴趣保存失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // 網路錯誤，顯示提示消息
                Log.e("ChooseActivity", "Network error: ${t.message}")
                Toast.makeText(this@ChooseActivity, "网络错误", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // 從服務器獲取用戶興趣列表
    private fun getUserInterestsFromServer() {
        apiService.getUserInterests(username).enqueue(object : Callback<InterestResponse> {
            override fun onResponse(call: Call<InterestResponse>, response: Response<InterestResponse>) {
                if (response.isSuccessful) {
                    // 獲取用戶興趣列表成功
                    val interests = response.body()?.interests

                    if (interests != null) {
                        // 如果興趣列表不為 null，跳轉到主畫面，並將興趣列表和推薦內容傳遞給 MainActivity
                        val intent = Intent(this@ChooseActivity, MainActivity::class.java)
                        intent.putStringArrayListExtra("userInterests", ArrayList(interests))
                        intent.putStringArrayListExtra(
                            "recommendedContent",
                            intent.getStringArrayListExtra("recommendedContent")
                        )
                        startActivity(intent)
                        finish()
                    } else {
                        // 如果興趣為 null，直接跳轉到 MainActivity
                        goToMainActivity()
                    }
                } else {
                    // 獲取興趣列表失敗，跳轉到 MainActivity
                    goToMainActivity()
                }
            }

            override fun onFailure(call: Call<InterestResponse>, t: Throwable) {
                // 網路錯誤，跳轉到 MainActivity
                goToMainActivity()
            }
        })
    }

    // 跳轉到 MainActivity
    private fun goToMainActivity() {
        val intent = Intent(this@ChooseActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
