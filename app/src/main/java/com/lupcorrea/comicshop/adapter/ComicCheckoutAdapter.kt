package com.lupcorrea.comicshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lupcorrea.comicshop.R
import com.lupcorrea.comicshop.model.ent.ComicReference

class ComicCheckoutAdapter (context: Context) : RecyclerView.Adapter<ComicCheckoutAdapter.ComicViewHolder>() {
    private val layoutInflater = LayoutInflater.from (context)
    var checkoutList = emptyList<ComicReference>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ComicViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        val comicTitle: TextView = itemView.findViewById(R.id.comic_title)
        val comicPrice: TextView = itemView.findViewById(R.id.comic_price)
        val comicAmount: TextView = itemView.findViewById(R.id.comic_amount)
        val comicPoster: ImageView = itemView.findViewById(R.id.comic_poster)

        val buttonRemove: ImageButton = itemView.findViewById (R.id.button_remove_from_cart)
        val buttonIncrease: ImageButton = itemView.findViewById (R.id.button_increase_amount)
        val buttonDecrease: ImageButton = itemView.findViewById (R.id.button_decrease_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val itemView = layoutInflater.inflate (R.layout.comic_checkout_item, parent, false)
        return ComicViewHolder (itemView)
    }

    override fun getItemCount() = checkoutList.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        /*TODO: Link with referenced comic
        val currentComic: Comic? = null

        // Setup prices as string
        val price = "$" + currentComic.price

        // Setup texts and image
        holder.comicTitle.text = currentComic.title
        holder.comicPrice.text = price
        holder.comicAmount.text = checkoutList [position].amount.toString()
        holder.comicPoster.setImageBitmap (currentComic.image)

        // Setup buttons
        holder.buttonRemove.setOnClickListener {
            //TODO: Remove from list and update UI
        }
        holder.buttonIncrease.setOnClickListener {
            //TODO: Change amount notifying dataset change (ViewModel)
            holder.comicAmount.text = checkoutList [position].amount.toString()
            //TODO: Calculate total price
        }
        holder.buttonDecrease.setOnClickListener {
            if (checkoutList [position].amount > 0) {
                //TODO: Change amount notifying dataset change (ViewModel)
                holder.comicAmount.text = checkoutList [position].amount.toString()
                //TODO: Calculate total price
            }
        }*/
    }

}