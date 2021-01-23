package com.demiter.usescases

import com.demiter.data.repository.ArtistRepository
import com.demiter.domain.Artista

class ToggleArtistFavorite(private val artistRepository: ArtistRepository) {

    suspend fun invoke(aritst: Artista): Artista {
        return with(aritst){
            copy(favorite = !favorite).also {
                artistRepository.update( it)
            }
        }
    }
}