package com.example.musicademi.model.database

import androidx.room.*


@Dao
interface ArtistaDao {

    @Query("SELECT * FROM Artista")
    fun getAllArtista(): List<Artista>

    @Query("SELECT COUNT(idArtist) FROM Artista")
    fun artistaCount(): Int

    @Query("SELECT * FROM Album WHERE nameArtistaShort = :nameArtist")
    fun getAllAlbumsByName(nameArtist: String): List<Album>

    @Query("SELECT COUNT(idAlbum) FROM Album WHERE nameArtistaShort = :nameArtist")
    fun albumCountByName(nameArtist: String):Int

    @Query("SELECT * FROM Artista WHERE mbidArtista = :mbid")
    fun findByMbidArtistDb(mbid: String): Artista

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    fun insertArtistas(artistas: List<Artista>)

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    fun insertAlbums(albums: List<Album>)

    @Update
    fun updateArtista(artista: Artista)

}