package com.matthew.albums.di

import com.matthew.albums.AlbumViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ViewModelInjector {
    /**
     * injects the required dependencies into specified viewmodel
     * @param albumViewModel AlbumViewModel in which to inject dependencies
     */
    fun inject(albumViewModel: AlbumViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
    }
}