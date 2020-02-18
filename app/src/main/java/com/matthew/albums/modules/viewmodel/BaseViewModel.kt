package com.matthew.albums.modules.viewmodel

import androidx.lifecycle.ViewModel
import com.matthew.albums.di.DaggerViewModelInjector
import com.matthew.albums.di.ViewModelInjector
import com.matthew.albums.di.modules.NetworkModule

abstract class BaseViewModel: ViewModel(){

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init{
        when(this){
            is AlbumListViewModel -> {
                injector.inject(this)
            }
        }

    }

}