package com.example.a112208

// MainActivity.kt

// 引入必要的庫
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast

// MainActivity 類別繼承 AppCompatActivity
class ScoreActivity : AppCompatActivity() {

    // 儲存留言的列表
    private val messages = mutableListOf<String>()

    // 延遲初始化 ArrayAdapter
    private lateinit var adapter: ArrayAdapter<String>

    // 覆寫 onCreate 方法
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 設置活動的布局
        setContentView(R.layout.activity_score)

        // 初始化 ArrayAdapter
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, messages)

        // 找到 ListView 並設置適配器
        val listView: ListView = findViewById(R.id.listViewMessages)
        listView.adapter = adapter

        // 找到 EditText 和 Button
        val editTextMessage: EditText = findViewById(R.id.editTextMessage)
        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)
        // 返回按鈕
        val btnreturn = findViewById<Button>(R.id.btnReturn)
        btnreturn.setOnClickListener {
            val intent = Intent(this@ScoreActivity, MovieinfoActivity::class.java)
            startActivity(intent)
        }
        // 設置 Button 的點擊監聽器
        buttonSubmit.setOnClickListener {
            // 獲取 EditText 中的留言
            val message = editTextMessage.text.toString()

            // 檢查留言是否不為空白
            if (message.isNotBlank()) {
                // 添加留言到列表中
                messages.add(message)

                // 通知適配器數據已更改
                adapter.notifyDataSetChanged()

                // 清空 EditText
                editTextMessage.text.clear()
            } else {
                // 若留言為空白，顯示提示
                Toast.makeText(this, "請輸入留言", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
