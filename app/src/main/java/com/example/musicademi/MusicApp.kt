package com.example.musicademi

import android.app.Application
import androidx.room.Room
import com.example.musicademi.di.MyMusicsComponent
import com.example.musicademi.di.DaggerMyMusicsComponent

import com.example.musicademi.model.database.MusicDatabase

class MusicApp: Application() {

    lateinit var component: MyMusicsComponent
    private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerMyMusicsComponent
            .factory()
            .create(this)
    }
}