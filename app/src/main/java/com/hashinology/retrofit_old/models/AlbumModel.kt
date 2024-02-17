package com.hashinology.retrofit_old.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("MOvieTable")
data class AlbumModel(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int? = null,
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
