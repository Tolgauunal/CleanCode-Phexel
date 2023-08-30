package com.unallapps.tko.data.api.service

import com.unallapps.tko.data.api.model.Photo
import com.unallapps.tko.data.api.model.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface PhotoService {

    @Headers(
        "Authorization: "
    )

    @GET("v1/search")
    suspend fun getAllPhotos(@Query("query") queryText: String): Response<List<Photo>>
}