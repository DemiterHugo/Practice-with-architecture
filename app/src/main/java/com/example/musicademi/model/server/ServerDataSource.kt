package com.example.musicademi.model.server

import com.demiter.data.source.RemoteDataSource
import com.demiter.domain.Album
import com.demiter.domain.Image
import com.example.musicademi.ImageArtist
import com.demiter.domain.Artista as DomainArtist
import com.example.musicademi.model.server.Artista as ServerArtista
import com.example.musicademi.model.server.Album as ServerAlbum

class ServerDataSource: RemoteDataSource {



    override suspend fun getPopularArtists(apiKey: String, region: String): List<DomainArtist> {
          return TheMusicDb.service.listTopArtistsAsync(region,
            apiKey).topartists.artists.map { artista -> toDomainArtist(artista) }


    }

    override suspend fun getPopularAlbums(name: String, apikey: String): List<Album> {
        var depurar = TheMusicDb.service.listTopAlbumsAsync(name,
            apikey).topalbums.albums.map { album -> album.toDomainAlbum() }
        depurar
        return depurar
    }


    fun ServerAlbum.toDomainAlbum(): Album {
        val imagesAlbums: MutableList<Image> = mutableListOf()

        this.image.forEach {
            imagesAlbums.add(Image(0, name, it.text,it.size))
        }
        return Album(0,name,playcount,url,artist.name,artist.mbid,artist.url,imagesAlbums)
    }

   fun toDomainArtist(serverArtista: ServerArtista): DomainArtist {

       val imagesArtist: MutableList<Image> = mutableListOf()

       serverArtista.image.forEach {
                imagesArtist.add(Image(0, serverArtista.mbid, ImageArtist().imageArtist(serverArtista.name), it.size))
           }

           return DomainArtist(0, serverArtista.name, serverArtista.listeners, serverArtista.mbid, serverArtista.url, serverArtista.streamable, false, imagesArtist)

    }
}
