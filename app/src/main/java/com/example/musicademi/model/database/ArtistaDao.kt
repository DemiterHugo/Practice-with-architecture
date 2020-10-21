package com.example.musicademi.model.database

import androidx.room.*


@Dao
interface ArtistaDao {

    @Query("SELECT * FROM Artista")
    fun getAllArtista(): List<Artista>

    @Query( "SELECT * FROM Artista WHERE id = :id")
    fun findById(id: Int): Artista

    @Query("SELECT COUNT(id) FROM Artista")
    fun artistaCount(): Int

    @Insert( onConflict = OnConflictStrategy.IGNORE)
    fun insertArtistas(artistas: List<Artista>)

    @Update
    fun updateArtista(artista: Artista)
}