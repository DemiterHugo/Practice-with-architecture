package com.demiter.usescases

import com.demiter.data.repository.ArtistRepository
import com.demiter.domain.Artista

class GetPopularArtists(private val artistRepository: ArtistRepository) {

    suspend fun invoke():List<Artista>{
        return artistRepository.getPopularArtists()
    }
}