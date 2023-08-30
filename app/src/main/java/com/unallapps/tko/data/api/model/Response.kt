package com.unallapps.tko.data.api.model

data class Response<T>(
    val page: Int,
    val per_page: Int,
    val photos: T,
    val next_page: String
)