package com.example.musicademi.model.database

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
data class Artista(
    @PrimaryKey(autoGenerate = true)
    val idArtist:Int,
    @ColumnInfo(name = "nameArtista")val name: String,
    val listeners: String,
    @ColumnInfo(name = "mbidArtista")val mbid: String,
    @ColumnInfo(name = "urlArtista")val url: String,
    val streamable: String,
    val favorite: Boolean,
)

@Entity
data class Image(
    @PrimaryKey(autoGenerate = true)
    val idImage: Int,
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
    val idAlbum: Int,
    @ColumnInfo(name = "nameAlbum")val name: String,
    val playcount: Int,
    @ColumnInfo(name = "mbidAlbum")val mbid: String,
    @ColumnInfo(name = "urlAlbum")val url: String,
    val nameArtistaShort: String,
    val mbidArtistaShort: String,
    val urlArtistaShort: String,
)

data class AlbumDb(
    @Embedded val album: Album,
    @Relation (parentColumn = "mbidAlbum", entityColumn = "creatorId")
    val imageAlbumDb: List<Image>
)



