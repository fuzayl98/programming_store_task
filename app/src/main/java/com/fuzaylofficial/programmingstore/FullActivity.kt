package com.fuzaylofficial.programmingstore

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL

class FullActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full)
        val extras = intent.extras ?: return

        val img = findViewById<ImageView>(R.id.full_image)

        val text = findViewById<TextView>(R.id.image_url);
        text.text = extras.getString("url")

        val urlImage:URL = URL(extras.getString("url")!!.replace("{width}", "256").replace("{height}", "256"))
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