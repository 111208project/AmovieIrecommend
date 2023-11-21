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

    private val messages = mutableListOf<String>()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        adapter = MessageAdapter(this, android.R.layout.simple_list_item_1, messages)

        val listView: ListView = findViewById(R.id.listViewMessages)
        listView.adapter = adapter

        val editTextMessage: EditText = findViewById(R.id.editTextMessage)
        val buttonSubmit: Button = findViewById(R.id.buttonSubmit)
        val btnreturn = findViewById<Button>(R.id.btnReturn)
        btnreturn.setOnClickListener {
            val intent = Intent(this@ScoreActivity, MovieinfoActivity::class.java)
            startActivity(intent)
        }

        buttonSubmit.setOnClickListener {
            val message = editTextMessage.text.toString()

            if (message.isNotBlank()) {
                messages.add(message)
                adapter.notifyDataSetChanged()
                editTextMessage.text.clear()
            } else {
                Toast.makeText(this, "請輸入留言", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private inner class MessageAdapter(
        context: Context,
        resource: Int,
        objects: List<String>
    ) : ArrayAdapter<String>(context, resource, objects) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getView(position, convertView, parent)
            val textView = view.findViewById<TextView>(android.R.id.text1)
            textView.textSize = 18f // 設置字體大小，這裡以 18sp 為例
            return view
        }
    }
}
