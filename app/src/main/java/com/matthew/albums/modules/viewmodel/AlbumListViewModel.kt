package com.matthew.albums.modules.viewmodel


import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.matthew.albums.di.DaggerViewModelInjector
import com.matthew.albums.di.ViewModelInjector
import com.matthew.albums.di.modules.NetworkModule
import com.matthew.albums.modules.ui.AlbumAdapter
import com.matthew.albums.network.AlbumApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class AlbumListViewModel: ViewModel(), SwipeRefreshLayout.OnRefreshListener {

    companion object{
        const val TAG = "ALBUM_LIST_VIEW_MODEL"
    }

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    @Inject
    lateinit var mApi: AlbumApi

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private val albumAdapter = AlbumAdapter()

    init{
        injector.inject(this)

        fetchAlbums()
    }

    override fun onRefresh() {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch(Dispatchers.Main) {
            try{
                loadingVisibility.postValue(View.VISIBLE)
                withTimeout(10000L) {
                    mApi.getAlubms().await().body()?.sortedBy{it.title}?.let{
                        Log.i(TAG,"Size is ${it.size}")
                        loadingVisibility.postValue(View.GONE)
                        albumAdapter.submitList(it)
                    }
                }

            }catch (timeout: Exception){
                loadingVisibility.postValue(View.GONE)
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