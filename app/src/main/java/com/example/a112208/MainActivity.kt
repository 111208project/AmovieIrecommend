package com.example.a112208

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.a112208.R
import com.example.a112208.fragments.FragmentHome
import com.example.a112208.fragments.Fragment_favorite
import com.example.a112208.fragments.Fragment_user
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // 底部導航欄選項點擊監聽器
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // 點擊首頁，顯示 FragmentHome
                    loadFragment(FragmentHome())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorite -> {
                    // 點擊喜好，顯示 Fragment_favorite，同時顯示 Toast 提示
                    loadFragment(Fragment_favorite())
                    Toast.makeText(this, "喜好", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    // 點擊個人檔案，顯示 Fragment_user，同時顯示 Toast 提示
                    loadFragment(Fragment_user.newInstance())
                    Toast.makeText(this, "個人檔案", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false
            }
            false
        }

    // 載入指定的 Fragment
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化時顯示 FragmentHome
        loadFragment(FragmentHome())

        // 獲取底部導航欄視圖
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        // 設定底部導航欄選項點擊監聽器
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
