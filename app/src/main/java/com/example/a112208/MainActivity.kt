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

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(FragmentHome())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorite -> {
                    loadFragment(Fragment_favorite())
                    Toast.makeText(this, "喜好", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    loadFragment(Fragment_user.newInstance())
                    Toast.makeText(this, "個人檔案", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false
            }
            false
        }

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

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
