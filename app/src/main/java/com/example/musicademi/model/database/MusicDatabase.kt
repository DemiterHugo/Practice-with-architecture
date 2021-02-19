package com.example.musicademi.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(version =1, entities = [Artista::class, Album::class] )
abstract class MusicDatabase: RoomDatabase() {

    abstract fun artistaDao(): ArtistaDao

    companion object{
        fun build(context: Context) = Room.databaseBuilder(
            context,
            MusicDatabase::class.java,
            "music-db"
        ).build()
    }
}