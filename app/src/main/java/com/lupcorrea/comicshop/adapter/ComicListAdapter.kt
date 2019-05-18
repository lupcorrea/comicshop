package com.lupcorrea.comicshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lupcorrea.comicshop.R
import com.lupcorrea.comicshop.model.ent.Comic

class ComicListAdapter (context: Context) : RecyclerView.Adapter<ComicViewHolder>() {
    private val layoutInflater = LayoutInflater.from (context)
    val comicList: List<Comic> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val itemView = layoutInflater.inflate(R.layout.comic_item, parent, false)
        return ComicViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return comicList.size
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.comicTitle.text = comicList[position].title
        holder.comicPrice.text = comicList[position].price
    }
}

class ComicViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
    val comicTitle: TextView = itemView.findViewById(R.id.list_comic_title)
    val comicPrice: TextView = itemView.findViewById(R.id.list_comic_price)
    //TODO: Poster
}