package com.example.musicademi

import android.app.Application
import androidx.room.Room
import com.example.musicademi.model.database.MusicDatabase

class ArtistasApp: Application() {

    lateinit var db: MusicDatabase
    private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this,MusicDatabase::class.java,"music-db").build()
    }
}