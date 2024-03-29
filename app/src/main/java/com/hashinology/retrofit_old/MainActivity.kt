package com.hashinology.retrofit_old

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hashinology.retrofit_old.adapter.AlbumAdapter
import com.hashinology.retrofit_old.data.RetrofitClient
import com.hashinology.retrofit_old.models.AlbumModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Collections

class MainActivity : AppCompatActivity(), ClickedNotifier {
    lateinit var albumAdapter: AlbumAdapter
    lateinit var recyclerView: RecyclerView
    val albumVM: AlbumVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        albumVM = AlbumVM(application)
        lifecycleScope.launch {
            albumVM.setUpRetrofit()
        }
        albumVM.getRetro.observe(this, Observer {
//            albumAdapter = AlbumAdapter(it)
            setUpRecyclerView(it)
        })

        val itemTouchHelper = ItemTouchHelper(simpleItemCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setUpRecyclerView(body: List<AlbumModel>) {
        albumAdapter = AlbumAdapter(body, this)
        recyclerView = findViewById(R.id.rvMovies)
        recyclerView.apply {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    val simpleItemCallback = object: ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or  ItemTouchHelper.START or  ItemTouchHelper.END, 0){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val startPostion = viewHolder.adapterPosition
            val endPostion = target.adapterPosition
            recyclerView.adapter!!.notifyItemMoved(startPostion, endPostion)
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            TODO("Not yet implemented")
        }
    }

    override fun getClciked(albumModel: AlbumModel) {
        lifecycleScope.launch {
            albumVM.insert(albumModel)
        }
        val intent = Intent(this@MainActivity, FavouritActivity::class.java)
//        intent.putExtra("albumModel", albumModel)
        startActivity(intent)
    }
}