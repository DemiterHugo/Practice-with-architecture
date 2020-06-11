package com.example.musicademi.model

import android.app.Activity
import com.example.musicademi.R
import com.example.musicademi.data.server.TheMusicDb
import com.example.musicademi.data.server.TheTopAlbumDbResult
import com.example.musicademi.ui.common.Scope

class AlbumsRepository(activity: Activity): Scope by Scope.Iml() {

    private val apiKey = activity.getString(R.string.apy_key)

    suspend fun findPopularAlbums(name: String): TheTopAlbumDbResult {
        return TheMusicDb.service.listTopAlbumsAsync(name,apiKey)
    }

}