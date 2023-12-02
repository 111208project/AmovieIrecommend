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

    private lateinit var apiService: ApiService
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        apiService = ApiClient.getApiClient().create(ApiService::class.java)

        // 從SharedPreferences中讀取用戶名
        val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        username = sharedPreferences?.getString("username", "") ?: ""

        val btnFavorite = view.findViewById<Button>(R.id.btnChoose)
        val checkbox1 = view.findViewById<CheckBox>(R.id.checkbox1)
        val checkbox2 = view.findViewById<CheckBox>(R.id.checkbox2)
        val checkbox3 = view.findViewById<CheckBox>(R.id.checkbox3)
        val checkbox4 = view.findViewById<CheckBox>(R.id.checkbox4)
        val checkbox5 = view.findViewById<CheckBox>(R.id.checkbox5)
        val checkbox6 = view.findViewById<CheckBox>(R.id.checkbox6)

        btnFavorite.setOnClickListener {
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
                Toast.makeText(activity, "請選擇至少一個興趣", Toast.LENGTH_SHORT).show()

            }
        }
        return view
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
                    Toast.makeText(activity, "興趣更改成功!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "興趣保存失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(activity, "網路錯誤", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
