package com.example.musicademi.model

import android.app.Activity
import com.example.musicademi.R
import com.example.musicademi.data.server.TheMusicDb
import com.example.musicademi.data.server.TheTopArtistsDbResult

class ArtistsRepository(activity: Activity) {

    private val regionRepository = RegionRepository(activity)
    private val apiKey = activity.getString(R.string.apy_key)

    suspend fun findPopularArtist(): TheTopArtistsDbResult {
        return TheMusicDb.service.listTopArtistsAsync(regionRepository.findLastRegion(),apiKey)
    }

}