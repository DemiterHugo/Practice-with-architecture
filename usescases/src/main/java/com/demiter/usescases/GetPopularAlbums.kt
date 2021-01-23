package com.demiter.usescases

import com.demiter.data.repository.AlbumsRepository
import com.demiter.domain.Album

class GetPopularAlbums(private val albumRepository: AlbumsRepository) {

    suspend fun invoke(): List<Album>{
       return albumRepository.getPopularAlbums()
    }
}