package com.hashinology.retrofit_old.data

import com.hashinology.retrofit_old.models.AlbumModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitClient {
    @GET("photos")
    fun getAlbumList(): Response<List<AlbumModel>>

    companion object{
        const val Base_URL = "https://jsonplaceholder.typicode.com/"
        fun getInstanceBuild(): RetrofitClient{
            var instance: RetrofitClient? = null
            if (instance == null){
                var retrofit = Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()

                instance = retrofit.create(RetrofitClient::class.java)
            }
            return instance!!
        }
    }
}