package com.unallapps.tko.data.state

import com.unallapps.tko.data.api.model.Photo

sealed class PhotoState {
    object Idle : PhotoState()
    class Result(val photo: List<Photo>) : PhotoState()
    class Error(val error: String) : PhotoState()
}