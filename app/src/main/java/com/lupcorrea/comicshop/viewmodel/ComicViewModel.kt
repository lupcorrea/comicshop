package com.lupcorrea.comicshop.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lupcorrea.comicshop.model.ent.Comic
import com.lupcorrea.comicshop.model.rep.ComicRepository

class ComicViewModel (application: Application) : AndroidViewModel (application) {
    private val repository = ComicRepository (application)
    val shoppingList = repository.shoppingList
    val checkoutList = repository.checkoutList

    fun fillShoppingList() = repository.fillShoppingList()

    fun addComicToCheckout (comic: Comic) = repository.addComicToCheckout (comic)
}