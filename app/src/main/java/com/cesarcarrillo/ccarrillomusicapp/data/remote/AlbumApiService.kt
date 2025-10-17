package com.cesarcarrillo.ccarrillomusicapp.data.remote

import com.cesarcarrillo.ccarrillomusicapp.data.model.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApiService {

    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun getAlbumById(@Path("id") id: Int): Album
}
