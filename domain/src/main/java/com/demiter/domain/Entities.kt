package com.demiter.domain

data class Artista(
    val idArtist:Int,
    val name: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val favorite: Boolean,
    val image: List<Image>
)

data class Image(
    val idImage: Int,
    val creatorId:String = "desconocido",
    var text:String,
    val size:String
)

data class Album(
    val idAlbum: Int,
    val name: String,
    val playcount: Int,
    //val mbidAlbum: String,
    val url: String,
    val nameArtistaShort: String,
    val mbidArtistaShort: String,
    val urlArtistaShort: String,
    val image: List<Image>
)