package com.hashinology.retrofit_old

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hashinology.retrofit_old.adapter.AlbumAdapter
import com.hashinology.retrofit_old.data.RetrofitClient
import com.hashinology.retrofit_old.models.AlbumModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var albumAdapter: AlbumAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofitClient = RetrofitClient.getInstanceBuild().getAlbumList()
        retrofitClient.enqueue(object : Callback<List<AlbumModel>> {
            override fun onResponse(
                call: Call<List<AlbumModel>>,
                response: Response<List<AlbumModel>>
            ) {
                if (response.body() != null){
                    setUpRecyclerView(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<AlbumModel>>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUpRecyclerView(body: List<AlbumModel>) {
        albumAdapter = AlbumAdapter(body)
        recyclerView = findViewById(R.id.rvMovies)
        recyclerView.apply {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}