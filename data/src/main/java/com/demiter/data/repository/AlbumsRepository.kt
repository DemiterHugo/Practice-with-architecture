package com.demiter.data.repository

import com.demiter.data.source.LocalDataSource
import com.demiter.data.source.RemoteDataSource
import com.demiter.domain.Album
import com.demiter.domain.Artista


class AlbumsRepository(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource,
    val nameArtist: String,
    val apiKey: String
) {

    suspend fun getPopularAlbums(): List<Album>{
        if (localDataSource.isEmptybyName(nameArtist)){
            val albums = remoteDataSource.getPopularAlbums(nameArtist, apiKey)
            localDataSource.saveAlbums(albums)
        }
        return localDataSource.getPopularAlbumsByName(nameArtist)
    }

}