package com.lupcorrea.comicshop.model.rep

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lupcorrea.comicshop.api.MarvelAPIConsumer
import com.lupcorrea.comicshop.model.ent.Comic
import com.lupcorrea.comicshop.model.ent.ComicReference

class ComicRepository (application: Application) {
    val marvel = MarvelAPIConsumer (application)

    val shoppingList = MutableLiveData<List<Comic>>()
    val checkoutList = MutableLiveData<List<ComicReference>>()

    init {
        shoppingList.value = emptyList<Comic>()
        checkoutList.value = emptyList<ComicReference>()

        fillShoppingList()
    }

    fun fillShoppingList() {
        marvel.requestComicList (25, shoppingList)
    }

    fun addComicToCheckout (comic: Comic) {
        val tempList = checkoutList.value!!.toMutableList()
        tempList.add (ComicReference (comic.id, 1))
        checkoutList.value = tempList.toList()
    }

}