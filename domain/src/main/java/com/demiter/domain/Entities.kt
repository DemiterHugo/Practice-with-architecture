package com.demiter.domain

data class Artista(
    val idArtist:Int,
    val name: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val favorite: Boolean,
    val image: String
)

/*data class Image(
    val idImage: Int,
    val creatorId:String,
    var text:String,
    val size:String
)*/

data class Album(
    val idAlbum: Int,
    val name: String,
    val playcount: Int,
    val url: String,
    val nameArtistaShort: String,
    val mbidArtistaShort: String,
    val urlArtistaShort: String,
    val image: String
)