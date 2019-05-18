package com.lupcorrea.comicshop.view.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lupcorrea.comicshop.R
import com.lupcorrea.comicshop.adapter.ComicListAdapter
import com.lupcorrea.comicshop.api.MarvelAPIConsumer

class ShopList : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated (view, savedInstanceState)

        val shopRecyclerView = view.findViewById<RecyclerView> (R.id.shop_recycler_view)
        shopRecyclerView.adapter = ComicListAdapter (view.context)
        shopRecyclerView.layoutManager = LinearLayoutManager (view.context)

        val api = MarvelAPIConsumer (this.activity!!.application)
        api.requestComicList (25, shopRecyclerView.adapter as ComicListAdapter)
    }
}