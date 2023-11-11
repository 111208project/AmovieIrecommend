package com.example.a112208

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.a112208.R
import com.example.a112208.fragments.Fragment_favorite
import com.example.a112208.fragments.Fragment_home
import com.example.a112208.fragments.Fragment_user
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(
                        Fragment_home.newInstance(
                            intent.getStringArrayListExtra("recommendedContent")
                                ?: arrayListOf()
                        )
                    )
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorite -> {
                    // 处理最爱的 Fragment
                    // 例如： loadFragment(FavoriteFragment.newInstance())
                    Toast.makeText(this, "Favorite clicked", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    loadFragment(Fragment_user.newInstance())
                    Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
                    return@OnNavigationItemSelectedListener true
                }
                else -> return@OnNavigationItemSelectedListener false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // 預設顯示第一個 Fragment
        loadFragment(
            Fragment_home.newInstance(
                intent.getStringArrayListExtra("recommendedContent") ?: arrayListOf()
            )
        )
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
