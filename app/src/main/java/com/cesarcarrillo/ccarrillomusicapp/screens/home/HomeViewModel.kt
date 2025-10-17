package com.cesarcarrillo.ccarrillomusicapp.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesarcarrillo.ccarrillomusicapp.data.model.Album
import com.cesarcarrillo.ccarrillomusicapp.data.remote.RetrofitInstance
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class AlbumUiState {
    object Loading : AlbumUiState()
    data class Success(val albums: List<Album>) : AlbumUiState()
    data class Error(val message: String) : AlbumUiState()
}

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<AlbumUiState>(AlbumUiState.Loading)
    val uiState: StateFlow<AlbumUiState> = _uiState

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch {
            try {
                val albums = RetrofitInstance.api.getAlbums()
                _uiState.value = AlbumUiState.Success(albums)
            } catch (e: Exception) {
                _uiState.value = AlbumUiState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}
