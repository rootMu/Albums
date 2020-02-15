package com.matthew.albums.modules.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.matthew.albums.R
import com.matthew.albums.databinding.ActivityAlbumListBinding
import com.matthew.albums.modules.viewmodel.AlbumListViewModel

class AlbumListActivity : AppCompatActivity() {

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
            binding.viewModel = this
        }
    }
}
