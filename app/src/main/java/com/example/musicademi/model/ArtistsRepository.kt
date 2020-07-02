package com.example.musicademi.model

import android.app.Activity
import android.app.Application
import com.example.musicademi.R
import com.example.musicademi.data.server.TheMusicDb
import com.example.musicademi.data.server.TheTopArtistsDbResult

class ArtistsRepository(application: Application) {

    private val regionRepository = RegionRepository(application)
    private val apiKey = application.getString(R.string.apy_key)

    suspend fun findPopularArtists(): TheTopArtistsDbResult {
        return TheMusicDb.service.listTopArtistsAsync(regionRepository.findLastRegion(),apiKey)
    }

}