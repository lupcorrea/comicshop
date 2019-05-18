package com.lupcorrea.comicshop.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lupcorrea.comicshop.view.CheckoutList
import com.lupcorrea.comicshop.view.ShopList

class ViewPagerAdapter (fm: FragmentManager): FragmentPagerAdapter (fm) {
    private val fragments = arrayOf (ShopList(), CheckoutList())

    override fun getItem (position: Int): Fragment {
        return fragments [position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}