package com.demiter.data.source

import com.demiter.domain.Album
import com.demiter.domain.Artista

interface RemoteDataSource{
    suspend fun getPopularArtists(apiKey: String, region: String): List<Artista>

    suspend fun getPopularAlbums(name: String, apikey: String): List<Album>
}