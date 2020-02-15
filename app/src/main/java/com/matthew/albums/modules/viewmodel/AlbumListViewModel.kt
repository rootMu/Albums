package com.matthew.albums.modules.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matthew.albums.di.DaggerViewModelInjector
import com.matthew.albums.di.ViewModelInjector
import com.matthew.albums.di.modules.NetworkModule
import com.matthew.albums.modules.ui.AlbumAdapter
import com.matthew.albums.network.AlbumApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class AlbumListViewModel: ViewModel() {

    companion object{
        const val TAG = "ALBUM_LIST_VIEW_MODEL"
    }

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    @Inject
    lateinit var mApi: AlbumApi

    private val albumAdapter = AlbumAdapter()

    init{
        injector.inject(this)

        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch(Dispatchers.Main) {
            try{

                withTimeout(10000L) {
                    mApi.getAlubms().await().body()?.let{
                        Log.i(TAG,"Size is ${it.size}")
                        albumAdapter.submitList(it)
                    }
                }

            }catch (timeout: Exception){
                onError(timeout)
            }
        }
    }

    private fun onError(exception: Exception){
        Log.e(TAG, "API Call failed", exception)
    }

    fun getAlbumAdapter(): AlbumAdapter{
        return albumAdapter
    }

}