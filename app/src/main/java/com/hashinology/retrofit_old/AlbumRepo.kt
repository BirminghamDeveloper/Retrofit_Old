package com.hashinology.retrofit_old

import com.hashinology.retrofit_old.Database.AlbumDao
import com.hashinology.retrofit_old.data.RetrofitClient
import com.hashinology.retrofit_old.models.AlbumModel

class AlbumRepo(val albumDao: AlbumDao) {
    suspend fun getUpsert(albumModel: AlbumModel) = albumDao.upsert(albumModel)
    suspend fun getUpdate(albumModel: AlbumModel) = albumDao.update(albumModel)
    suspend fun getDelete(albumModel: AlbumModel) = albumDao.delete(albumModel)
    val albumLiveData = albumDao.getAlbumList()
    suspend fun getByID(searchID: Int) = albumDao.getByID(searchID)
    suspend fun getAlbumRetrofitClient() = RetrofitClient.getInstanceBuild().getAlbumList()
}