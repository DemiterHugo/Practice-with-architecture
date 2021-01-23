package com.demiter.data.repository

import com.demiter.data.source.LocalDataSource
import com.demiter.data.source.RemoteDataSource
import com.demiter.domain.Artista

class ArtistRepository(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource,
    val regionRepository: RegionRepository,
    val apiKey: String
) {

    suspend fun getPopularArtists(): List<Artista>{
        if (localDataSource.isEmpty()){
            val artists = remoteDataSource.getPopularArtists(apiKey, regionRepository.findLastRegion())
            localDataSource.saveArtists(artists)
        }
        return localDataSource.getPopularArtists()
    }

    suspend fun findByMbid(mbid: String):Artista{
        return localDataSource.findByMbid(mbid)
    }

    suspend fun update(artista: Artista){
        localDataSource.update(artista)
    }
}


