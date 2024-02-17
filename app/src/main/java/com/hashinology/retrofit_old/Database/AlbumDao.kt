package com.hashinology.retrofit_old.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hashinology.retrofit_old.models.AlbumModel

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(albumModel: AlbumModel)
    @Update()
    suspend fun update(albumModel: AlbumModel)
    @Delete()
    suspend fun delete(albumModel: AlbumModel)
    @Query("Select * From MOvieTable")
    fun getAlbumList(): LiveData<List<AlbumModel>>

    @Query("Select * From MOvieTable where id = :searchID")
    suspend fun getByID(searchID: Int): AlbumModel?
}