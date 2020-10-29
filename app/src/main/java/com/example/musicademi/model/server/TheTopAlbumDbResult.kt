package com.example.musicademi.model.server

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TheTopAlbumDbResult(
    val topalbums: TopAlbums
)

@Parcelize
data class TopAlbums(
    @SerializedName("album") val albums: List<Album>,
    @SerializedName("@attr") val attr: Atributtes
):Parcelable

@Parcelize
data class Album(
    val name:String,
    val playcount:Int,
    val mbid:String,
    val url:String,
    val artist: Artist,
    val image:List<Image>
):Parcelable

@Parcelize
data class Artist(val name: String, val mbid: String, val url: String):Parcelable



@Parcelize
data class Atributtes(val artist:String, val page:String, val perPage: String, val totalPages:String, val total:String):Parcelable