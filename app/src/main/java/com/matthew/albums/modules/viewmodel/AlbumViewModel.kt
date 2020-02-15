package com.matthew.albums.modules.viewmodel

import androidx.lifecycle.ViewModel
import com.matthew.albums.di.DaggerViewModelInjector
import com.matthew.albums.di.ViewModelInjector
import com.matthew.albums.di.modules.NetworkModule
import com.matthew.albums.network.AlbumApi
import javax.inject.Inject

class AlbumViewModel: ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    @Inject
    lateinit var mApi: AlbumApi

    init{
        injector.inject(this)
    }

}