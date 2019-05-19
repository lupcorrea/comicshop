package com.lupcorrea.comicshop.model.rep

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.lupcorrea.comicshop.api.MarvelAPIConsumer
import com.lupcorrea.comicshop.model.ent.Comic

class ComicRepository (application: Application) {
    val marvel = MarvelAPIConsumer (application)

    val shoppingList = MutableLiveData<List<Comic>>()
    val checkoutList = MutableLiveData<List<Comic>>()

    init {
        shoppingList.value = emptyList<Comic>()
        checkoutList.value = emptyList<Comic>()

        fillShoppingList()
    }

    fun fillShoppingList() {
        marvel.requestComicList (25, shoppingList)
    }

}