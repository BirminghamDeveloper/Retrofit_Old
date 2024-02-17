package com.hashinology.retrofit_old

import com.hashinology.retrofit_old.models.AlbumModel

interface ClickedNotifier {
    fun getClciked(albumModel: AlbumModel)
}