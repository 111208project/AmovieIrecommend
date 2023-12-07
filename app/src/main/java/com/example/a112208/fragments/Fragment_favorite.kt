package com.example.a112208.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.a112208.R
import com.example.a112208.api.ApiClient
import com.example.a112208.api.ApiService
import com.example.a112208.api.InterestRequest
import com.example.a112208.api.InterestResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Fragment_favorite : Fragment() {

    // 声明 ApiService 和用户名变量
    private lateinit var apiService: ApiService
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 載入 Fragment 的布局
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        // 初始化 ApiService
        apiService = ApiClient.getApiClient().create(ApiService::class.java)

        // 從 SharedPreferences 中讀取用戶名
        val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        username = sharedPreferences?.getString("username", "") ?: ""

        // 獲取相關的視圖元素
        val btnFavorite = view.findViewById<Button>(R.id.btnChoose)
        val checkbox1 = view.findViewById<CheckBox>(R.id.checkbox1)
        val checkbox2 = view.findViewById<CheckBox>(R.id.checkbox2)
        val checkbox3 = view.findViewById<CheckBox>(R.id.checkbox3)
        val checkbox4 = view.findViewById<CheckBox>(R.id.checkbox4)
        val checkbox5 = view.findViewById<CheckBox>(R.id.checkbox5)
        val checkbox6 = view.findViewById<CheckBox>(R.id.checkbox6)

        // 設定按鈕的點擊監聽器
        btnFavorite.setOnClickListener {
            // 創建一個 List 來保存選中的興趣
            val interests = mutableListOf<String>()

            // 檢查每個 CheckBox 是否被選中，若是，將對應的興趣加入 List
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

            // 如果選中的興趣不為空，則將其發送到伺服器
            if (interests.isNotEmpty()) {
                sendInterestsToServer(interests)
            } else {
                // 如果選中的興趣為空，顯示提示
                Toast.makeText(activity, "請選擇至少一個興趣", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    // 將選中的興趣發送到伺服器的方法
    private fun sendInterestsToServer(interests: List<String>) {
        // 如果用戶名為空，可能需要重新登录或其他操作，直接返回
        if (username.isNullOrEmpty()) {
            return
        }
        // 創建 InterestRequest 對象，包含選中的興趣和用戶名
        val request = InterestRequest(interests, username)
        // 使用 Retrofit 向伺服器發送興趣請求
        apiService.sendInterests(request, username).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // 興趣保存成功，顯示成功提示
                    Toast.makeText(activity, "興趣更改成功!", Toast.LENGTH_SHORT).show()
                } else {
                    // 興趣保存失敗，顯示失敗提示
                    Toast.makeText(activity, "興趣保存失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // 網路錯誤，顯示網路錯誤提示
                Toast.makeText(activity, "網路錯誤", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
