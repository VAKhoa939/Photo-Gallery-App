package com.example.photogallery.data

import android.content.Context
import com.example.photogallery.R
import com.example.photogallery.model.Photo

class Photosource {
    fun fetchAllPhotos(): List<Photo> {
        return listOf(
            Photo(R.drawable._054973468),
            Photo(R.drawable._064210191),
            Photo(R.drawable._081798706),
            Photo(R.drawable._134244681),
            Photo(R.drawable._253214059),
            Photo(R.drawable._265835291),
            Photo(R.drawable._361627293),
            Photo(R.drawable._474399744),
            Photo(R.drawable._484290705),
            Photo(R.drawable._515928520),
            Photo(R.drawable._567102774),
            Photo(R.drawable._603749343),
            Photo(R.drawable._687833871),
            Photo(R.drawable._703255528),
            Photo(R.drawable._705575566),
            Photo(R.drawable._709838505),
            Photo(R.drawable._724762948),
            Photo(R.drawable._733760937),
            Photo(R.drawable._853410110),
            Photo(R.drawable._948572900)
        )
    }

    fun getPhotoName(photo: Photo, context: Context): String {
        return context.resources.getResourceEntryName(photo.photoResourceId)
    }
}