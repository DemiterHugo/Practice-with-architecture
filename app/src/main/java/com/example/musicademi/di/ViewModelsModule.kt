package com.example.musicademi.di

import com.demiter.usescases.FindArtistByMbid
import com.demiter.usescases.GetPopularAlbums
import com.demiter.usescases.GetPopularArtists
import com.demiter.usescases.ToggleArtistFavorite
import com.example.musicademi.ui.common.ScopedViewModel
import com.example.musicademi.ui.detail.DetailViewModel
import com.example.musicademi.ui.main.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelsModule {

    @Provides
    fun mainViewModelProvider(getPopularArtists: GetPopularArtists)
    = MainViewModel(getPopularArtists)

    @Provides
    fun detailViewModelProvider(
        //mbidArtista:String,
        findArtistByMbid: FindArtistByMbid,
        getPopularAlbums: GetPopularAlbums,
        toggleArtistFavorite: ToggleArtistFavorite
    ):DetailViewModel{
        return DetailViewModel("mbidArtista",findArtistByMbid,getPopularAlbums,toggleArtistFavorite)
    }
}

