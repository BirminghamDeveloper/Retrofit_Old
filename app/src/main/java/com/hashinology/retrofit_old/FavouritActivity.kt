package com.hashinology.retrofit_old

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hashinology.retrofit_old.adapter.AlbumAdapter
import com.hashinology.retrofit_old.models.AlbumModel
import kotlinx.coroutines.launch

class FavouritActivity : AppCompatActivity(), ClickedNotifier {
    lateinit var albumAdapter: AlbumAdapter
    lateinit var recyclerView: RecyclerView
    val albumVM: AlbumVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourit)

        albumVM.getAlbumInfo.observe(this, Observer {
            setUpRecyclerView(it)
        })

        val itemTouchHelper = ItemTouchHelper(simpleItemCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setUpRecyclerView(body: List<AlbumModel>) {
        albumAdapter = AlbumAdapter(body, this)
        recyclerView = findViewById(R.id.rvFavouriteMovies)
        recyclerView.apply {
            adapter = albumAdapter
            layoutManager =
                LinearLayoutManager(this@FavouritActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    val simpleItemCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
        0
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            /*val startPostion = viewHolder.adapterPosition
            val endPostion = target.adapterPosition
            recyclerView.adapter!!.notifyItemMoved(startPostion, endPostion)
            */
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val albumList = albumAdapter.list.get(viewHolder.adapterPosition)
            lifecycleScope.launch{
                albumVM.delete(albumList)
            }
        }
    }

    override fun getClciked(albumModel: AlbumModel) {

    }
}