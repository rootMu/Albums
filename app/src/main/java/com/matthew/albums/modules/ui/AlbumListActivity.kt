package com.matthew.albums.modules.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.matthew.albums.R
import com.matthew.albums.databinding.ActivityAlbumListBinding
import com.matthew.albums.modules.viewmodel.AlbumListViewModel
import com.matthew.albums.modules.ui.AlbumUiModel.*
import com.matthew.albums.nonNullObserve
import java.lang.Exception
import javax.inject.Inject

class AlbumListActivity : AppCompatActivity() {

    companion object{
        const val TAG_ERROR_POPUP = "TAG_ERROR_POPUP"
    }

    private lateinit var mListViewModel: AlbumListViewModel
    private lateinit var binding: ActivityAlbumListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialiseDataBinding()
        initialiseViewModel()
    }

    private fun initialiseDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_list)
    }

    private fun initialiseViewModel() {

        mListViewModel = ViewModelProviders.of(this).get(AlbumListViewModel::class.java).apply{
            viewState.nonNullObserve(this@AlbumListActivity){
                when(it){
                    is Error -> {
                        handleError(it.exception)
                    }
                }
            }
            binding.viewModel = this
        }
    }

    private fun handleError(exception: Exception){
        ErrorDialog(exception).show(
            supportFragmentManager,
            TAG_ERROR_POPUP
        )
    }
}
