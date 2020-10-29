package com.example.musicademi.model.server

import com.example.musicademi.ImageArtist
import com.example.musicademi.MusicApp
import com.example.musicademi.R
import com.example.musicademi.model.RegionRepository
import com.example.musicademi.model.database.ArtistDb
import com.example.musicademi.model.database.Artista
import com.example.musicademi.model.database.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.musicademi.model.server.Artista as ArtistaServer
import com.example.musicademi.model.server.Image as ImageServer


class ArtistsRepository(musicApp: MusicApp) {

    private val regionRepository = RegionRepository(musicApp)
    private val apiKey = musicApp.getString(R.string.apy_key)
    private val db = musicApp.db


    suspend fun findPopularArtists():List<ArtistDb> {
        return withContext(Dispatchers.IO){
            with(db.artistaDao()){
                if (artistaCount() <= 0){
                    val artistServer = TheMusicDb.service.listTopArtistsAsync(regionRepository.findLastRegion(),apiKey).topartists.artists
                    insertArtistas(artistServer.map { artista ->convertToArtistDb(artista) })
                }
                getAllArtista()
            }

        }
    }

    private fun convertToArtistDb(artistServer: ArtistaServer):Artista{

        with(artistServer){
            this.image.forEach {
                db.artistaDao().insertImages(Image(0,this.mbid ,ImageArtist().imageArtist(this.name),it.size))
            }
            return Artista(0, name, listeners, mbid, url, streamable, false)
        }
    }

}