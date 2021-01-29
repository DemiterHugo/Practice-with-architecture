package com.example.musicademi.model.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(version =1, entities = [Artista::class, Album::class] )
abstract class MusicDatabase: RoomDatabase() {

    abstract fun artistaDao(): ArtistaDao
}