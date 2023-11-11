package com.example.a112208.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.a112208.LoginActivity
import com.example.a112208.R
import com.example.a112208.RegisterActivity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.EditText
import android.content.SharedPreferences
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

private lateinit var sharedPreferences: SharedPreferences

class Fragment_user : Fragment() {

    companion object {
        fun newInstance(param1: String = "DefaultParam2"): Fragment_user {

            val fragment = Fragment_user()
            val args = Bundle()
            args.putString("param1", param1)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 載入並設置 fragment_user 佈局
        val view = inflater.inflate(R.layout.fragment_user, container, false)


        // 獲取變更密碼和登出按鈕
        val changePasswordButton: Button = view.findViewById(R.id.button3)
        val logoutButton: Button = view.findViewById(R.id.logout)

        // 獲取共享偏好設置
        sharedPreferences = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "0")
        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = username

        // 設置變更密碼按鈕的點擊監聽器
        changePasswordButton.setOnClickListener {
            // 創建 AlertDialog 以輸入新密碼
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("變更密碼")
            builder.setMessage("請輸入新密碼")

            // 創建 EditText 以輸入新密碼
            val newPasswordEditText = EditText(requireContext())
            builder.setView(newPasswordEditText)

            // 設置確定按鈕的點擊監聽器
            builder.setPositiveButton("確定") { dialog: DialogInterface, which: Int ->
                val newPassword = newPasswordEditText.text.toString()

                // 將新密碼存入SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("password", newPassword)
                editor.apply()

                // 在這裡處理新密碼，這裡可以添加相應的邏輯

                // 關閉對話框
                dialog.dismiss()
            }

            // 獲取已儲存的密碼
            val savedPassword = sharedPreferences.getString("password", "")
            val textView3 = view.findViewById<TextView>(R.id.textView3)

            // 如果已儲存的密碼不為空，則顯示在TextView中
            if (!savedPassword.isNullOrBlank()) {
                textView3.text = "密碼：$savedPassword"
            } else {
                // 如果找不到密碼，可以採取適當的處理方式
            }

            // 設置取消按鈕的點擊監聽器
            builder.setNegativeButton("取消") { dialog: DialogInterface, which: Int ->
                // 關閉對話框
                dialog.dismiss()
            }

            // 創建並顯示對話框
            val dialog: AlertDialog = builder.create()
            dialog.show()


        }

        // 設置登出按鈕的點擊監聽器
        logoutButton.setOnClickListener {
            // 啟動登入活動
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        // 返回此片段的視圖
        return view
    }

}

