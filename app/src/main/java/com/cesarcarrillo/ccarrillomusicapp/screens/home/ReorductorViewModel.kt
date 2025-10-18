package com.cesarcarrillo.ccarrillomusicapp.screens.home

import androidx.lifecycle.ViewModel
import com.cesarcarrillo.ccarrillomusicapp.data.model.Album
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlayerViewModel : ViewModel() {

    private val _currentAlbum = MutableStateFlow<Album?>(null)
    val currentAlbum: StateFlow<Album?> = _currentAlbum

    fun setCurrentAlbum(album: Album) {
        _currentAlbum.value = album
    }


}

