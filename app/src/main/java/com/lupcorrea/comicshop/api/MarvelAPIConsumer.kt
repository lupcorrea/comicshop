package com.lupcorrea.comicshop.api

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.lupcorrea.comicshop.model.ent.Comic
import kotlin.random.Random


// TODO: Incorporate MD5 generator into code

class MarvelAPIConsumer (app: Application) {
    private val url = "https://gateway.marvel.com:443/v1/public/comics"

    private val key = "347bd7bcb2cbbded2083684e6814346c"
    private val timestamp = "1"
    private val hash = "1983bd6bcbfa92c4d18d4cb7cc859154"
    private val requestEnd = "ts=$timestamp&apikey=$key&hash=$hash"

    private val queue = Volley.newRequestQueue (app.applicationContext)

    fun requestComicList (limit: Int, comicList: MutableLiveData<List<Comic>>) {
        val requestURL = "$url?format=comic&orderBy=-issueNumber&limit=$limit&$requestEnd"

        val request = JsonObjectRequest (Request.Method.GET, requestURL, null,
            Response.Listener { response ->
                val comics = response.getJSONObject ("data").getJSONArray ("results")
                val tempList = mutableListOf<Comic>()
                val rareIndexes = List ((limit * 0.12).toInt()) { Random.nextInt (0, limit) }

                for (c in 0 until limit) {
                    val comic = comics.getJSONObject (c)

                    val id = comic.getString ("id")

                    val title = comic.getString ("title")

                    val imagePath = comic.getJSONObject ("thumbnail").getString ("path") +
                            "/portrait_uncanny." +
                            comic.getJSONObject ("thumbnail").getString ("extension")

                    var creators = ""
                    val creatorsArray = comic.getJSONObject ("creators").getJSONArray ("items")
                    if (creatorsArray.length() > 0) creators += creatorsArray.getJSONObject (0).getString ("name")
                    if (creatorsArray.length() > 1) {
                        for (creatorIndex in 1 until creatorsArray.length()) {
                            creators += ", " + creatorsArray.getJSONObject(creatorIndex).getString("name")
                            if (creatorIndex == 3) {
                                creators += ", and others."
                                break
                            }
                        }
                    }

                    val series = comic.getJSONObject ("series").getString ("name")

                    val price = comic.getJSONArray ("prices").getJSONObject (0).getDouble ("price")

                    val description = comic.getString ("description")

                    val isRare = rareIndexes.contains (c)

                    if (c == limit-1) requestImage (imagePath, Comic (id, title, null, creators, description, price, isRare), tempList, comicList)
                    else requestImage (imagePath, Comic (id, title, null, creators, description, price, isRare), tempList, null)
                }
            }, Response.ErrorListener {
                Log.e("API Request", it.toString())
            })

        queue.add (request)
    }

    fun requestImage (imagePath: String, comic: Comic, tempList: MutableList<Comic>, comicList: MutableLiveData<List<Comic>>?) {
        val imageRequest = ImageRequest (imagePath,
            Response.Listener { response: Bitmap ->
                comic.image = response
                tempList.add (comic)
                if (comicList != null) {
                    comicList.value = tempList.toList()
                }
            }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,
            Response.ErrorListener {
                Log.e ("API Request", comic.id)
            })

        queue.add (imageRequest)
    }
}