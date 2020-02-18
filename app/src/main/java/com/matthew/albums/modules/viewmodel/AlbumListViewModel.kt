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
import com.matthew.albums.modules.ui.AlbumUiModel
import com.matthew.albums.modules.ui.AlbumUiModel.*
import com.matthew.albums.network.AlbumApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumListViewModel: BaseViewModel(), SwipeRefreshLayout.OnRefreshListener {

    companion object{
        const val TAG = "ALBUM_LIST_VIEW_MODEL"
    }

    @Inject
    lateinit var mApi: AlbumApi

    val viewState: MutableLiveData<AlbumUiModel> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    private val albumAdapter = AlbumAdapter()

    init{
        fetchAlbums()
    }

    override fun onRefresh() {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        viewModelScope.launch(Dispatchers.Main){
            try{
                loadingVisibility.postValue(View.VISIBLE)
//                withTimeout(10000L) {
                    mApi.getAlubms().await().body()?.sortedBy{it.title}?.let{
                        Log.i(TAG,"Size is ${it.size}")
                        loadingVisibility.postValue(View.GONE)
                        albumAdapter.submitList(it)
//                    }
                }

            }catch (timeout: Exception){
                loadingVisibility.postValue(View.GONE)
                onError(timeout)
            }
        }
    }

    private fun onError(exception: Exception){
        when(exception){
            is TimeoutCancellationException -> {
                Log.e(TAG, "Search Timed out", exception)
            }
            else -> {
                Log.e(TAG, "API Call failed", exception)
            }
        }

        viewState.postValue(Error(exception))
    }

    fun getAlbumAdapter(): AlbumAdapter{
        return albumAdapter
    }

}