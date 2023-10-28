package com.example.a112208

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View


class PasswordChangeDialog(private val context: Context) {

    fun showChangePasswordDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Change Password")

        // 使用自定義佈局
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.activity_password, null)
        builder.setView(dialogView)

        builder.setPositiveButton("Save") { dialog, which ->
            // 在此處處理密碼更改的邏輯

        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            // 使用者取消操作
        }

        val dialog = builder.create()
        dialog.show()
    }
}
