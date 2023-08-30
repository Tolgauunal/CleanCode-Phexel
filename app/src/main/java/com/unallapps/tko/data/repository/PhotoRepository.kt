package com.unallapps.tko.data.repository

import com.unallapps.tko.data.api.model.Photo

interface PhotoRepository {

    suspend fun getAllPhoto(category: String): List<Photo>
}