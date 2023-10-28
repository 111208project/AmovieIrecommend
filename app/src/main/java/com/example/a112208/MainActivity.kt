package com.example.a112208

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.a112208.fragments.Fragment_favorite
import com.example.a112208.fragments.Fragment_home
import com.example.a112208.fragments.Fragment_user
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                loadFragment(Fragment_home.newInstance(intent.getStringArrayListExtra("recommendedContent") ?: arrayListOf()))
                return@OnNavigationItemSelectedListener true
            }
            R.id.btnLeft -> {
                loadFragment(Fragment_favorite.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            // 這裡可以加入其他的 Fragment
            R.id.btnRight -> {
                loadFragment(Fragment_user.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            else ->return@OnNavigationItemSelectedListener false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // 預設顯示第一個 Fragment
        loadFragment(Fragment_home.newInstance(intent.getStringArrayListExtra("recommendedContent") ?: arrayListOf()))
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
