package com.example.musicademi.model.database

import androidx.room.*

@Entity
data class Artista(
    @PrimaryKey(autoGenerate = true)
    val idArtist: Int,
    @ColumnInfo(name = "nameArtista") val name: String,
    val listeners: String,
    @ColumnInfo(name = "mbidArtista") val mbid: String,
    @ColumnInfo(name = "urlArtista") val url: String,
    val streamable: String,
    val favorite: Boolean,
    val image: String
)

@Entity
data class Album(
    @PrimaryKey(autoGenerate = true)
    val idAlbum: Int=0,
    @ColumnInfo(name = "nameAlbum")val name: String,
    val playcount: Int,
    @ColumnInfo(name = "urlAlbum")val url: String,
    val nameArtistaShort: String,
    val mbidArtistaShort: String,
    val urlArtistaShort: String,
    val image: String
)
