package com.matthew.albums.modules.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.matthew.albums.R
import com.matthew.albums.modules.viewmodel.AlbumListViewModel

class AlbumActivity : AppCompatActivity() {

    private lateinit var mListViewModel: AlbumListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        initialiseViewModel()
    }

    private fun initialiseViewModel() {
        mListViewModel = ViewModelProviders.of(this).get(AlbumListViewModel::class.java)
    }
}
