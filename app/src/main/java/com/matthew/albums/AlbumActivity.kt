package com.matthew.albums

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders

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
