package com.example.musicademi

import android.app.Application
import androidx.room.Room
import com.example.musicademi.di.MyMusicsComponent
import com.example.musicademi.di.DaggerMyMusicsComponent

import com.example.musicademi.model.database.MusicDatabase

class MusicApp: Application() {



    override fun onCreate() {
        super.onCreate()
        initDI()
    }
}