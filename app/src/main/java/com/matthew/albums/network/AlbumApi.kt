package com.matthew.albums.network

import com.matthew.albums.network.model.Album
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface AlbumApi {

    /**
     * Get a list of albums
     */
    @GET("/albums")
    fun getAlubms(): Deferred<Response<List<Album>>>

}