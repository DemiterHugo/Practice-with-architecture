package com.example.musicademi

import android.app.Application
import com.demiter.data.repository.AlbumsRepository
import com.demiter.data.repository.ArtistRepository
import com.demiter.data.source.PermissionChecker
import com.demiter.data.repository.RegionRepository
import com.demiter.data.source.LocalDataSource
import com.demiter.data.source.LocationDataSource
import com.demiter.data.source.RemoteDataSource
import com.demiter.usescases.FindArtistByMbid
import com.demiter.usescases.GetPopularAlbums
import com.demiter.usescases.GetPopularArtists
import com.demiter.usescases.ToggleArtistFavorite
import com.example.musicademi.model.AndroidPermissionChecker
import com.example.musicademi.model.PlayServicesLocationDataSource
import com.example.musicademi.model.database.MusicDatabase
import com.example.musicademi.model.database.RoomDataSource
import com.example.musicademi.model.server.ServerDataSource
import com.example.musicademi.model.server.TheMusicDb
import com.example.musicademi.ui.detail.DetailActivity
import com.example.musicademi.ui.detail.DetailViewModel
import com.example.musicademi.ui.main.MainActivity
import com.example.musicademi.ui.main.MainViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI(){
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single(named("apikey")){androidApplication().getString(R.string.apy_key)}
    single{MusicDatabase.build(get()) }
    factory <LocalDataSource>{RoomDataSource(get())  }
    factory <RemoteDataSource>{ServerDataSource(get())  }
    factory <LocationDataSource>{PlayServicesLocationDataSource(get())  }
    factory<PermissionChecker> { AndroidPermissionChecker(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single(named("baseUrl")) { "http://ws.audioscrobbler.com/2.0/" }
    single { TheMusicDb(get(named("baseUrl"))) }
}

private val dataModule = module {
    factory {RegionRepository(get(),get())  }
    factory {ArtistRepository(get(),get(),get(),get(named("apikey")))  }
    factory {AlbumsRepository(get(),get(),"Radiohead",get(named("apikey")))  }
}

private val scopesModule = module{

    scope(named<MainActivity>()){
        viewModel { MainViewModel(get(),get()) }
        scoped { GetPopularArtists(get()) }
    }

    scope(named<DetailActivity>()){
        viewModel { (mbidA: String) -> DetailViewModel(mbidA,get(),get(),get(),get()) }
        scoped {FindArtistByMbid(get())  }
        scoped{GetPopularAlbums(get())}
        scoped {ToggleArtistFavorite(get()) }
        scoped {AlbumsRepository(get(),get(),"Radiohead",get(named("apikey")))  }

    }
}