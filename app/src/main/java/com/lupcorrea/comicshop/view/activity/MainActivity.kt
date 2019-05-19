package com.lupcorrea.comicshop.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.lupcorrea.comicshop.R
import com.lupcorrea.comicshop.adapter.ViewPagerAdapter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager> (R.id.view_pager)
        viewPager.adapter = ViewPagerAdapter (supportFragmentManager)

        /* TODO:
        val tabLayout = findViewById<TabLayout> (R.id.tabs_main)
        tabLayout.setupWithViewPager (viewPager)
        */
    }
}
