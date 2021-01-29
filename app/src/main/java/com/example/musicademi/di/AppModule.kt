package com.example.musicademi.di

import android.app.Application
import androidx.room.Room
import com.demiter.data.repository.PermissionChecker
import com.demiter.data.source.LocalDataSource
import com.demiter.data.source.LocationDataSource
import com.demiter.data.source.RemoteDataSource
import com.example.musicademi.R
import com.example.musicademi.model.AndroidPermissionChecker
import com.example.musicademi.model.PlayServicesLocationDataSource
import com.example.musicademi.model.database.MusicDatabase
import com.example.musicademi.model.database.RoomDataSource
import com.example.musicademi.model.server.ServerDataSource
import com.example.musicademi.model.server.TheMusicDb
import com.example.musicademi.model.server.TheTopArtistsDbResult
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    @Named("apikey")
    fun apikeyProvider(app:Application):String = app.getString(R.string.apy_key)

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(app, MusicDatabase::class.java,"movie-db").build()

    @Provides
    fun localDataSourceProvider(db: MusicDatabase):LocalDataSource
            = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider():RemoteDataSource
            = ServerDataSource()        //TheMovieDbDataSource

    @Provides
    fun locationDataSourceProvider(app: Application): LocationDataSource
            = PlayServicesLocationDataSource(app)

    @Provides
    fun permissionCheckerProvider(app: Application): PermissionChecker
            = AndroidPermissionChecker(app)
}