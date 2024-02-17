package com.hashinology.retrofit_old

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hashinology.retrofit_old.Database.AlbumDB
import com.hashinology.retrofit_old.models.AlbumModel
import retrofit2.Retrofit
import java.lang.Exception

class AlbumVM(context: Application): AndroidViewModel(context) {
    val repo: AlbumRepo
    val getAlbumInfo: LiveData<List<AlbumModel>>

    private var _getRetro = MutableLiveData<List<AlbumModel>>()
    val getRetro: LiveData<List<AlbumModel>> = _getRetro

    // init its for the Room
    init {
        val dao = AlbumDB.getInstance(context).getDBDao()
        repo = AlbumRepo(dao)

        getAlbumInfo = repo.albumLiveData
    }

    suspend fun setUpRetrofit(){
        val response = repo.getAlbumRetrofitClient()
        try {
            if (response.isSuccessful){
                _getRetro.postValue(response.body())
            }
        }catch (e: Exception){

        }
    }

    suspend fun insert(albumModel: AlbumModel) = repo.getUpsert(albumModel)
    suspend fun delete(albumModel: AlbumModel) = repo.getDelete(albumModel)
}
