package com.matthew.albums.modules.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.matthew.albums.R
import com.matthew.albums.modules.viewmodel.AlbumViewModel

class AlbumActivity : AppCompatActivity() {

    private lateinit var mViewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        initialiseViewModel()
    }

    private fun initialiseViewModel() {
        mViewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)
    }
}
