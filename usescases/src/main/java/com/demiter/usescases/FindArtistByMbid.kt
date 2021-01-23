package com.demiter.usescases

import com.demiter.data.repository.ArtistRepository
import com.demiter.domain.Artista

class FindArtistByMbid(private val artistRepository: ArtistRepository) {

    suspend fun invoke(mbid: String):Artista{
        return artistRepository.findByMbid(mbid)
    }
}