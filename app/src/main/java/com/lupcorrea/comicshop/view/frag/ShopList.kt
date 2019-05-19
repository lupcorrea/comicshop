package com.lupcorrea.comicshop.view.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lupcorrea.comicshop.R
import com.lupcorrea.comicshop.adapter.ComicListAdapter
import com.lupcorrea.comicshop.viewmodel.ComicViewModel

class ShopList : Fragment() {
    private lateinit var comicViewModel: ComicViewModel

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
        val adapter = ComicListAdapter (context!!)
        shopRecyclerView.adapter = adapter
        shopRecyclerView.layoutManager = LinearLayoutManager (view.context)

        comicViewModel = ViewModelProviders.of (context as FragmentActivity).get (ComicViewModel::class.java)
        comicViewModel.shoppingList.observe (this, Observer { comics ->
            comics?.let { adapter.comicList = comics}
        })
    }
}