package com.example.a112208

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    // 存儲留言的列表
    private val messages = mutableListOf<String>()
    private lateinit var adapter: MessageAdapter

    @SuppressLint("ViewHolder")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        // 初始化 ArrayAdapter 並設置給 ListView
        adapter = MessageAdapter(this, android.R.layout.simple_list_item_1, messages)
        val listView: ListView = findViewById(R.id.listViewMessages)
        listView.adapter = adapter

        // 獲取相關的視圖元素
        val editTextMessage: EditText = findViewById(R.id.editTextMessage)
        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)
        val btnReturn = findViewById<Button>(R.id.btnReturn)

        // 設定"Return"按鈕的點擊監聽器
        btnReturn.setOnClickListener {
            val intent = Intent(this@ScoreActivity, MovieinfoActivity::class.java)
            startActivity(intent)
        }

        // 設定"Submit"按鈕的點擊監聽器
        buttonSubmit.setOnClickListener {
            // 獲取輸入的留言
            val message = editTextMessage.text.toString()

            // 檢查留言是否為空
            if (message.isNotBlank()) {
                // 將留言加入列表，並通知 adapter 更新
                messages.add(message)
                adapter.notifyDataSetChanged()
                editTextMessage.text.clear()
            } else {
                // 如果留言為空，顯示提示
                Toast.makeText(this, "請輸入留言", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 自定義 ArrayAdapter 用於修改 TextView 字體大小
    private inner class MessageAdapter(
        context: Context,
        resource: Int,
        objects: List<String>
    ) : ArrayAdapter<String>(context, resource, objects) {

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            // 獲取視圖元素
            val view = super.getView(position, convertView, parent)
            val textView = view.findViewById<TextView>(android.R.id.text1)

            // 設置字體大小，這裡以 18sp 為例
            textView.textSize = 18f
            return view
        }
    }
}
