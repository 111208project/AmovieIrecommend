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
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        val changePasswordButton: Button = view.findViewById(R.id.button3)
        val logoutButton: Button = view.findViewById(R.id.logout)
        sharedPreferences = requireContext().getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        changePasswordButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("變更密碼")
            builder.setMessage("請輸入新密碼")

            val newPasswordEditText = EditText(requireContext())
            builder.setView(newPasswordEditText)

            builder.setPositiveButton("確定") { dialog: DialogInterface, which: Int ->
                val newPassword = newPasswordEditText.text.toString()
                // 將新密碼存入SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("password", newPassword)
                editor.apply()

                // 在這裡處理新密碼
                dialog.dismiss()

            }
            val savedPassword = sharedPreferences.getString("password", "")
            val textView3 = view.findViewById<TextView>(R.id.textView3)
            if (!savedPassword.isNullOrBlank()) {
                // 在這裡處理已儲存的密碼
                // 例如，你可以將它顯示在TextView中
                textView3.text = "密碼：$savedPassword"
            } else {
                // 如果找不到密碼，可以採取適當的處理方式
            }
            builder.setNegativeButton("取消") { dialog: DialogInterface, which: Int ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }

        logoutButton.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        return view

    }


}

