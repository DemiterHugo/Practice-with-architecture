package com.example.musicademi.ui.main

import com.demiter.data.repository.ArtistRepository
import com.demiter.usescases.GetPopularArtists
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
class MainActivityModule {

    @Provides
    fun mainViewModelProvider(getPopularArtists: GetPopularArtists, uiDispatcher: CoroutineDispatcher) =
        MainViewModel(getPopularArtists,uiDispatcher)

    @Provides
    fun getPopularArtistsProvider(artistRepository: ArtistRepository) =
        GetPopularArtists(artistRepository)
}


    @Subcomponent(modules = [(MainActivityModule::class)])
    interface MainActivityComponent{
        val mainViewModel: MainViewModel
    }
