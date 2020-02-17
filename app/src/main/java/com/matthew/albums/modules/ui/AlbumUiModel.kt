package com.matthew.albums.modules.ui

import java.lang.Exception

sealed class AlbumUiModel {
    data class Error(val exception: Exception) : AlbumUiModel()
}
