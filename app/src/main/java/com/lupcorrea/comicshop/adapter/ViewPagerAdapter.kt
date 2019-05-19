package com.lupcorrea.comicshop.adapter

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lupcorrea.comicshop.view.frag.CheckoutList
import com.lupcorrea.comicshop.view.frag.ShopList

class ViewPagerAdapter (fm: FragmentManager): FragmentPagerAdapter (fm) {
    private val fragments = arrayOf (
        ShopList(),
        CheckoutList()
    )

    override fun getItem (position: Int) = fragments [position]

    override fun getCount() = fragments.size

    override fun getPageTitle (position: Int): CharSequence? {
        return when (position) {
            0 -> "Shopping"
            1 -> "Checkout"
            else -> ""
        }
    }
}