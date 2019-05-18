package com.lupcorrea.comicshop.api

import android.app.Application
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.lupcorrea.comicshop.adapter.ComicListAdapter
import com.lupcorrea.comicshop.model.ent.Comic

// TODO: Incorporate MD5 generator into code

class MarvelAPIConsumer (app: Application) {
    private val url = "https://gateway.marvel.com:443/v1/public/comics"

    private val key = "347bd7bcb2cbbded2083684e6814346c"
    private val timestamp = "1"
    private val hash = "1983bd6bcbfa92c4d18d4cb7cc859154"
    private val requestEnd = "ts=$timestamp&apikey=$key&hash=$hash"

    private val queue = Volley.newRequestQueue (app.applicationContext)

    fun requestComicList (limit: Int, adapter: ComicListAdapter) {
        val requestURL = "$url?format=comic&orderBy=-issueNumber&limit=$limit&$requestEnd"

        val request = JsonObjectRequest (Request.Method.GET, requestURL, null,
            Response.Listener { response ->
                val comics = response.getJSONObject ("data").getJSONArray ("results")

                for (c in 0 until limit-1) {
                    val comic = comics.getJSONObject (c)
                    //TODO: real data for what is hardcoded.
                    adapter.addComic (Comic (
                        comic.getString("title"),
                        "Ukulele",
                        comic.getString ("pageCount"),
                        "Ukulele",
                        "Ukulele",
                        comic.getString ("issueNumber"),
                        "Ukulele"
                    ))
                }

            }, Response.ErrorListener {
                Log.e("API Request", it.toString())
            })

        queue.add (request)
    }

    // TODO: fix this method
    fun requestComic (comicId: Int) {
        val requestURL = "$url/$comicId&$requestEnd"

        val request = JsonObjectRequest (Request.Method.GET, requestURL, null,
            Response.Listener { response ->
                Log.e("API Request", response.getString("code"))
            }, Response.ErrorListener {
                Log.e("API Request", it.toString())
            })

        queue.add (request)
    }
}