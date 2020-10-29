package com.example.musicademi.model.server

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TheTopArtistsDbResult(val topartists: TopArtists)

data class TopArtists(@SerializedName("artist") val artists: List<Artista>,
                      @SerializedName("@attr") val attr: Attributes
)
@Parcelize
data class Artista(
    val name: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    val image: List<Image>
):Parcelable

@Parcelize
data class Image(@SerializedName("#text") var text:String, val size:String):Parcelable

@Parcelize
data class Attributes(
    val country: String,
    val page: String,
    val perPage: String,
    val totalPages: String,
    val total: String
):Parcelable