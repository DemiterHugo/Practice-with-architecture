package com.demiter.data.source

import com.demiter.domain.Album
import com.demiter.domain.Artista
import javax.swing.event.ListDataEvent


interface LocalDataSource{
    suspend fun isEmpty(): Boolean
    suspend fun isEmptybyName(nameArtista: String): Boolean
    suspend fun getPopularArtists(): List<Artista>
    suspend fun getPopularAlbumsByName(nameArtista: String): List<Album>
    suspend fun saveArtists(artists: List<Artista>)
    suspend fun saveAlbums(abums: List<Album>)
    suspend fun findByMbid(mbid: String):Artista
    suspend fun update(artist: Artista)
}