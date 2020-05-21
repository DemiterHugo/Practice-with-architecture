package com.example.musicademi.data.server

import com.google.gson.annotations.SerializedName

data class TheTopArtistsDbResult(val topartists: TopArtists)

data class TopArtists(@SerializedName("artist") val artists: List<Artista>,
                      @SerializedName("@attr") val attr: Attributes
)

data class Artista(
    val name: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val image: List<Image>
)

data class Attributes(val country: String, val page: String, val perPage: String, val totalPages: String, val total: String)