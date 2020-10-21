package com.example.musicademi.model.server

import android.app.Application
import com.example.musicademi.R
import com.example.musicademi.model.server.TheMusicDb
import com.example.musicademi.model.server.TheTopAlbumDbResult
import com.example.musicademi.ui.common.Scope

class AlbumsRepository(application: Application): Scope by Scope.Iml() {

    private val apiKey = application.getString(R.string.apy_key)

    suspend fun findPopularAlbums(name: String): TheTopAlbumDbResult {
        return TheMusicDb.service.listTopAlbumsAsync(name,apiKey)
    }

}