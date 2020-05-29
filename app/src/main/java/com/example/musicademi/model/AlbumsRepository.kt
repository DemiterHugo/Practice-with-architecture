package com.example.musicademi.model

import android.app.Activity
import com.example.musicademi.R
import com.example.musicademi.data.server.TheMusicDb
import com.example.musicademi.data.server.TheTopAlbumDbResult
import com.example.musicademi.loadUrl
import com.example.musicademi.ui.common.CoroutineScopeActivity
import kotlinx.android.synthetic.main.activity_detail.*

class AlbumsRepository(activity: Activity): CoroutineScopeActivity() {

    private val apiKey = activity.getString(R.string.apy_key)

    suspend fun findPopularAlbums(name: String): TheTopAlbumDbResult {
        return TheMusicDb.service.listTopAlbumsAsync(name,apiKey)
    }




}