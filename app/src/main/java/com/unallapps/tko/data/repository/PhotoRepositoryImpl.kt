package com.unallapps.tko.data.repository

import com.unallapps.tko.data.api.model.Photo
import com.unallapps.tko.data.api.service.PhotoService
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(val photoService: PhotoService) : PhotoRepository {
    override suspend fun getAllPhoto(category:String): List<Photo> {

        return photoService.getAllPhotos(category).photos
    }
}