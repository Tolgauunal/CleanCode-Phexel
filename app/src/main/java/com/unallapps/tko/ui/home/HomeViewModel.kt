package com.unallapps.tko.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unallapps.tko.data.repository.PhotoRepository
import com.unallapps.tko.data.state.PhotoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(val photoRepository: PhotoRepository) : ViewModel() {

    private val _photoListState: MutableStateFlow<PhotoState> = MutableStateFlow(PhotoState.Idle)
    val photoListState: StateFlow<PhotoState> = _photoListState

    fun getAllPhoto(categoryName: String) {
        viewModelScope.launch {
            runCatching {
                photoRepository.getAllPhoto(categoryName)?.let {
                    _photoListState.value =
                        PhotoState.Result(photoRepository.getAllPhoto(categoryName))
                } ?: kotlin.run {
                    _photoListState.value = PhotoState.Error("Değer yok")
                }

            }.onFailure {
                _photoListState.value = PhotoState.Error(it.message.toString())
            }

        }

    }
}