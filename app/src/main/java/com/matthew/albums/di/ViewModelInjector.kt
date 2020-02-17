package com.matthew.albums.di

import com.matthew.albums.di.modules.NetworkModule
import com.matthew.albums.modules.viewmodel.AlbumListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * injects the required dependencies into specified viewModel
     * @param albumListViewModel AlbumListViewModel in which to inject dependencies
     */
    fun inject(albumListViewModel: AlbumListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}