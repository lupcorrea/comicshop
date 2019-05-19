package com.lupcorrea.comicshop.model.ent

import android.graphics.Bitmap

data class Comic (val id: String, val title: String, var image: Bitmap?, val creators: String, val description: String, val price: Double) {
    val commonDiscountedPrice = "%.2f".format (price * 0.9)
    val rareDiscountedPrice = "%.2f".format (price * 0.75)
}