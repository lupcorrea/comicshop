package com.lupcorrea.comicshop.model.ent

import android.graphics.Bitmap

data class Comic (val id: Int, val title: String, var image: Bitmap?, val creators: String, val description: String, val price: Double, var isRare: Boolean) {
    val commonDiscountedPrice = "%.2f".format (price * 0.9)
    val rareDiscountedPrice = "%.2f".format (price * 0.75)
}