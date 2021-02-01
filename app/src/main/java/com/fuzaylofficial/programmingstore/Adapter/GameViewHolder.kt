package com.fuzaylofficial.programmingstore.Adapter

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.fuzaylofficial.programmingstore.FullActivity
import com.fuzaylofficial.programmingstore.MainActivity
import com.fuzaylofficial.programmingstore.R
import com.fuzaylofficial.programmingstore.RetrofitData.Games.Game
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL

class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var img: ImageView = itemView.findViewById(R.id.image_of_game)
    fun Bind(game: Game) {

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, FullActivity::class.java)
            intent.putExtra("url",game.getArt_url())
            itemView.context.startActivity(intent)
        }
        val urlImage:URL = URL(game.art_url!!.replace("{width}", "256").replace("{height}", "256"))
        val result: Deferred<Bitmap?> = GlobalScope.async { urlImage.toBitmap() }

        GlobalScope.launch(Dispatchers.Main) {
            val bitmap : Bitmap? = result.await()
            bitmap?.apply {
                img.setImageBitmap(bitmap)
            }
        }



    }
    fun URL.toBitmap(): Bitmap?{
        return try {
            BitmapFactory.decodeStream(openStream())
        }catch (e: IOException){
            null
        }
    }
}