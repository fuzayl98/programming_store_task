package com.fuzaylofficial.programmingstore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fuzaylofficial.programmingstore.Adapter.GameDiffUtillCallback
import com.fuzaylofficial.programmingstore.Adapter.GamesAdapter
import com.fuzaylofficial.programmingstore.RetrofitData.Games.Game

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.game_recycler_view)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(false)
        recyclerView.isNestedScrollingEnabled = false
        val viewModel = GamesViewModel(application)
        val adapter = GamesAdapter(GameDiffUtillCallback())
        viewModel.itemPagedList.observe(this@MainActivity, Observer { pagedList: PagedList<Game?>? -> adapter.submitList(pagedList) })
        recyclerView.adapter = adapter
    }
}