package com.matthew.albums.modules.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matthew.albums.network.model.Album

class AlbumViewModel: ViewModel(){
    private val albumTitle = MutableLiveData<String?>()
    private val albumId = MutableLiveData<Int>()
    private val albumUserId = MutableLiveData<Int>()

    fun bind(newAlbum: Album){
        with(newAlbum){
            albumTitle.postValue(title)
            albumId.postValue(id)
            albumUserId.postValue(userId)
        }
    }

    fun getAlbumTitle(): MutableLiveData<String?> {
        return albumTitle
    }

    fun getAlbumId(): MutableLiveData<Int> {
        return albumId
    }

    fun getAlbumUserId(): MutableLiveData<Int> {
        return albumUserId
    }

}