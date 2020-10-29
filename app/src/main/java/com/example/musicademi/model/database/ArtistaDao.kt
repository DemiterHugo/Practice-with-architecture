package com.example.musicademi.model.database

import androidx.room.*


@Dao
interface ArtistaDao {

    @Transaction
    @Query("SELECT * FROM Artista")
    fun getAllArtista(): List<ArtistDb>

    @Transaction
    @Query( "SELECT * FROM Artista WHERE idArtist = :id")
    fun findByIdArtista(id: Int): ArtistDb

    @Transaction
    @Query("SELECT COUNT(idArtist) FROM Artista")
    fun artistaCount(): Int

    @Transaction
    @Insert( onConflict = OnConflictStrategy.IGNORE)
    fun insertArtistas(artistas: List<Artista>)

    @Transaction
    @Insert( onConflict = OnConflictStrategy.IGNORE)
    fun insertAlbums(albums: List<Album>)


    @Insert( onConflict = OnConflictStrategy.IGNORE)
    fun insertImages(images: Image)

    @Transaction
    @Update
    fun updateArtista(artista: Artista)

    //-----------------------------------------------------

    @Transaction
    @Query("SELECT * FROM Album")
    fun getAllAlbums(): List<AlbumDb>

    @Transaction
    @Query("SELECT * FROM Album WHERE nameArtistaShort = :nameArtist")
    fun getAllAlbumsByName(nameArtist: String): List<AlbumDb>

    @Transaction
    @Query( "SELECT * FROM Album WHERE idAlbum = :id")
    fun findByIdAlbum(id: Int): AlbumDb

    @Transaction
    @Query("SELECT COUNT(idAlbum) FROM Album")
    fun albumCount(): Int

    @Transaction
    @Query("SELECT COUNT(idAlbum) FROM Album WHERE nameArtistaShort = :nameArtist")
    fun albumCountByName(nameArtist: String):Int

    @Transaction
    @Query("SELECT * FROM Artista WHERE mbidArtista = :mbid")
    fun findByMbidArtistDb(mbid: String): ArtistDb



    @Transaction
    @Update
    fun updateAlbum(album: Album)
}