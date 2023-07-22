package com.example.a112208

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import android.view.MenuItem
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


class MainActivity : AppCompatActivity() {
    //private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPagerAdapter= ViewPagerAdapter(this)
        val viewPager = findViewById<ViewPager2>(R.id.mViewPageNav)
        val btmNav = findViewById<BottomNavigationView>(R.id.btmNav)
        viewPager.adapter = viewPagerAdapter
       // navigationView = findViewById(R.id.btmNav)

         //val menuItem: MenuItem? = navigationView.menu.findItem(R.id.btnMid)
        // menuItem?.isChecked = true


        viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                btmNav.selectedItemId = when(position){
                    0->R.id.btnMid
                    1->R.id.btnLeft
                    2->R.id.btnRight
                    else->R.id.btnRight
                }
            }
        })


        findViewById<BottomNavigationView>(R.id.btmNav).setOnItemSelectedListener(
            NavigationBarView.OnItemSelectedListener{
            when(it.itemId){
                R.id.btnMid->{
                    viewPager.currentItem=0
                    return@OnItemSelectedListener true
                }
                R.id.btnLeft->{
                    viewPager.currentItem=1
                    return@OnItemSelectedListener true
                }
                R.id.btnRight->{
                    viewPager.currentItem=2
                    return@OnItemSelectedListener true
                }
            }
            false
        })
    }
}

class ViewPagerAdapter(Activity: MainActivity) : FragmentStateAdapter(Activity) {

    override fun getItemCount()=3

    override fun createFragment(position: Int)= when(position){
        0->fragment_home.newInstance("","")
        1->fragment_favorite.newInstance("","")
        2->fragment_user.newInstance("","")
        else->fragment_home()
    }

}
