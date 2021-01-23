package com.example.musicademi.model.database

import androidx.room.*

@Entity
data class Artista(
    @PrimaryKey(autoGenerate = true)
    val idArtist: Int = 0,
    @ColumnInfo(name = "nameArtista") val name: String,
    val listeners: String,
    @ColumnInfo(name = "mbidArtista") val mbid: String,
    @ColumnInfo(name = "urlArtista") val url: String,
    val streamable: String,
    val favorite: Boolean,
)

@Entity
data class Image(
    @PrimaryKey(autoGenerate = true)
    val idImage: Int=0,
    val creatorId:String,
    var text:String,
    val size:String
)

data class ArtistDb(
    @Embedded val artista :Artista,
    @Relation (parentColumn = "mbidArtista", entityColumn = "creatorId")
    val imageArtist: List<Image>
)

@Entity
data class Album(
    @PrimaryKey(autoGenerate = true)
    val idAlbum: Int=0,
    @ColumnInfo(name = "nameAlbum")val name: String,
    val playcount: Int,
   // @ColumnInfo(name = "mbidAlbum")val mbid: String,
    @ColumnInfo(name = "urlAlbum")val url: String,
    val nameArtistaShort: String,
    val mbidArtistaShort: String,
    val urlArtistaShort: String,
)

data class AlbumDb(
    @Embedded val album: Album,
    @Relation (parentColumn = "nameAlbum", entityColumn = "creatorId")
    val imageAlbumDb: List<Image>
)



