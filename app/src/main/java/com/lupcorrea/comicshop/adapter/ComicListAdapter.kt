package com.lupcorrea.comicshop.adapter

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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

        val buttonInformation: ImageButton = itemView.findViewById(R.id.list_comic_get_info)
        val buttonAddToCart: ImageButton = itemView.findViewById(R.id.list_comic_add_to_cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val itemView = layoutInflater.inflate(R.layout.comic_item, parent, false)
        return ComicViewHolder(itemView)
    }

    override fun getItemCount() = comicList.size

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val currentComic = comicList [position]

        // Setup prices as string
        val price = "$" + currentComic.price
        val commonDiscount = "$" + currentComic.commonDiscountedPrice
        val rareDiscount = "$" + currentComic.rareDiscountedPrice

        // Setup texts and image
        holder.comicTitle.text = currentComic.title
        holder.comicPrice.text = price
        holder.comicDiscount.text = commonDiscount
        holder.comicDiscountRare.text = rareDiscount
        holder.comicPoster.setImageBitmap (currentComic.image)

        // Setup buttons
        holder.buttonInformation.setOnClickListener {
            // Inflate layout
            val infoPopup = LayoutInflater.from (it.context).inflate (R.layout.comic_information, null, false)

            // Setup texts and image
            infoPopup.findViewById<TextView>(R.id.comic_title).text = currentComic.title
            infoPopup.findViewById<TextView>(R.id.comic_creators).text = currentComic.creators
            infoPopup.findViewById<TextView>(R.id.comic_price).text = price
            infoPopup.findViewById<TextView>(R.id.comic_discount_common).text = commonDiscount
            infoPopup.findViewById<TextView>(R.id.comic_discount_rare).text = rareDiscount
            infoPopup.findViewById<TextView>(R.id.comic_description).text = currentComic.description
            infoPopup.findViewById<TextView>(R.id.comic_description).movementMethod = ScrollingMovementMethod()
            // This is only allowed in API 26, sadly: infoPopup.findViewById<TextView>(R.id.comic_description).justificationMode = JUSTIFICATION_MODE_INTER_WORD
            infoPopup.findViewById<ImageView>(R.id.comic_poster).setImageBitmap (currentComic.image)

            // Turn into popup
            val popupWindow = PopupWindow (infoPopup, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            popupWindow.showAtLocation (it, Gravity.CENTER, 0, 0)

            // Set up buttons
            infoPopup.findViewById<ImageButton>(R.id.button_add_to_cart).setOnClickListener {
                //TODO: Add to cart
            }
            infoPopup.findViewById<ImageButton>(R.id.button_return_to_list).setOnClickListener {
                popupWindow.dismiss()
            }
        }
        holder.buttonAddToCart.setOnClickListener {
            // TODO: Add to cart
        }
    }
}