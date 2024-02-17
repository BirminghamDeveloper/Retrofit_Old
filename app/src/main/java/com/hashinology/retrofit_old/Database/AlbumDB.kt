package com.hashinology.retrofit_old.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hashinology.retrofit_old.models.AlbumModel

@Database(
    entities = arrayOf(AlbumModel::class),
    version = 1,
)
abstract class AlbumDB: RoomDatabase() {
    abstract fun getDBDao(): AlbumDao

    companion object{
        private var instance: AlbumDB? = null
        fun getInstance(context: Context): AlbumDB{
          if (instance == null){
            synchronized(AlbumDB::class.java){
                if (instance == null){
                    instanceSetup(context)
                }
            }
          }
            return instance!!
        }

        private fun instanceSetup(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AlbumDB::class.java,
            "albumDB"
        ).build()
    }
}
