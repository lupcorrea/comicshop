package com.lupcorrea.comicshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lupcorrea.comicshop.R
import com.lupcorrea.comicshop.model.ent.Comic

class ComicListAdapter (context: Context) : RecyclerView.Adapter<ComicListAdapter.ComicViewHolder>() {
    private val layoutInflater = LayoutInflater.from (context)
    var comicList = emptyList<Comic>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ComicViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        val comicTitle: TextView = itemView.findViewById(R.id.list_comic_title)
        val comicPrice: TextView = itemView.findViewById(R.id.list_comic_price)
        val comicDiscount: TextView = itemView.findViewById(R.id.list_comic_discount_common)
        val comicDiscountRare: TextView = itemView.findViewById(R.id.list_comic_discount_rare)
        val comicPoster: ImageView = itemView.findViewById(R.id.list_comic_poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val itemView = layoutInflater.inflate(R.layout.comic_item, parent, false)
        return ComicViewHolder(itemView)
    }

    override fun getItemCount() = comicList.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val price = "$" + comicList[position].price
        val commonDiscount = "$" + comicList[position].commonDiscountedPrice
        val rareDiscount = "$" + comicList[position].commonDiscountedPrice

        holder.comicTitle.text = comicList[position].title
        holder.comicPrice.text = price
        holder.comicDiscount.text = commonDiscount
        holder.comicDiscountRare.text = rareDiscount
        holder.comicPoster.setImageBitmap (comicList[position].image)
    }
}