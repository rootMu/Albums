package com.matthew.albums

import androidx.lifecycle.ViewModel
import com.matthew.albums.di.DaggerViewModelInjector
import com.matthew.albums.di.ViewModelInjector

class AlbumViewModel: ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .build()

    init{
        injector.inject(this)
    }

}