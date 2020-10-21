package com.example.musicademi.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Artista(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name: String,
    val listeners: String,
    val mbid: String,
    val url: String,
    val streamable: String,
    //val image: List<Image>,
    val favorite: Boolean
)

@Entity
data class Image(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text:String, val size:String)


