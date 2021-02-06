package com.example.musicademi.model.server

import com.demiter.data.source.RemoteDataSource

import com.example.musicademi.ImageArtist
import com.demiter.domain.Artista as DomainArtist
import com.demiter.domain.Album as DomainAlbum
import com.example.musicademi.model.server.Artista as ServerArtista
import com.example.musicademi.model.server.Album as ServerAlbum

class ServerDataSource(private val theMusicDb: TheMusicDb): RemoteDataSource {



    override suspend fun getPopularArtists(apiKey: String, region: String): List<DomainArtist> {
          return theMusicDb.service.listTopArtistsAsync(
              region,
            apiKey
          ).topartists.artists.map { artista -> serverToDomainArtist(artista) }


    }

    override suspend fun getPopularAlbums(name: String, apikey: String): List<DomainAlbum> {
        return theMusicDb.service.listTopAlbumsAsync(
            name,
            apikey
        ).topalbums.albums.map { album -> album.serverToDomainAlbum() }

    }


    fun ServerAlbum.serverToDomainAlbum(): DomainAlbum {
        with(this){
            return DomainAlbum(
                0,
                name,
                playcount,
                url,
                artist.name,
                artist.mbid,
                artist.url,
                image[2].text.toString()
            )
        }

    }

   fun serverToDomainArtist(serverArtista: ServerArtista): DomainArtist {
       with(serverArtista){
           return DomainArtist(
               0,
               name,
               listeners,
               mbid,
               url,
               streamable,
               false,
               ImageArtist().imageArtist(serverArtista.name)
           )
       }
    }
}
