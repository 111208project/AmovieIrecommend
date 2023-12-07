package com.example.a112208

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View

class PasswordChangeDialog(private val context: Context) {

    // 顯示更改密碼對話框
    fun showChangePasswordDialog() {
        // 創建 AlertDialog.Builder 實例
        val builder = AlertDialog.Builder(context)
        // 設定對話框標題
        builder.setTitle("Change Password")

        // 使用自定義佈局
        val inflater = LayoutInflater.from(context)
        // 載入自定義佈局
        val dialogView = inflater.inflate(R.layout.activity_password, null)
        builder.setView(dialogView)

        // 設定"Save"按鈕的點擊監聽器
        builder.setPositiveButton("Save") { dialog, which ->
            // 在此處處理密碼更改的邏輯
            // 這裡應該加入處理密碼更改的相應邏輯，例如獲取新密碼，進行驗證，然後進行實際的密碼更改操作
        }

        // 設定"Cancel"按鈕的點擊監聽器
        builder.setNegativeButton("Cancel") { dialog, which ->
            // 使用者取消操作
        }

        // 創建並顯示 AlertDialog
        val dialog = builder.create()
        dialog.show()
    }
}
