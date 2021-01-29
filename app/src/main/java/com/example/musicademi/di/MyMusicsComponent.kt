package com.example.musicademi.di

import android.app.Application
import com.example.musicademi.ui.detail.DetailActivityComponent
import com.example.musicademi.ui.detail.DetailActivityModule
import com.example.musicademi.ui.detail.DetailViewModel
import com.example.musicademi.ui.main.MainActivityComponent
import com.example.musicademi.ui.main.MainActivityModule
import com.example.musicademi.ui.main.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface MyMusicsComponent {

    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: DetailActivityModule): DetailActivityComponent

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app:Application): MyMusicsComponent
    }

}