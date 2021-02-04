package com.example.musicademi.ui.detail

import com.demiter.data.repository.AlbumsRepository
import com.demiter.data.repository.ArtistRepository
import com.demiter.data.source.LocalDataSource
import com.demiter.data.source.RemoteDataSource
import com.demiter.usescases.FindArtistByMbid
import com.demiter.usescases.GetPopularAlbums
import com.demiter.usescases.ToggleArtistFavorite
import com.example.musicademi.ui.common.ScopedViewModel
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

@Module
class DetailActivityModule(private val mbidbartista:String, private val nameArtist: String){

    @Provides
    fun albumRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        @Named("apikey")apikey: String,
        // @Named("nameArtist")nameArtist:String
    ) = AlbumsRepository(localDataSource,remoteDataSource,nameArtist,apikey)

    @Provides
    fun detailViewModelProvider(
        findArtistByMbid: FindArtistByMbid,
        getPopularAlbums: GetPopularAlbums,
        toggleArtistFavorite: ToggleArtistFavorite,
        uiDispatcher: CoroutineDispatcher
    ):DetailViewModel{
        return DetailViewModel(mbidbartista,findArtistByMbid,getPopularAlbums,toggleArtistFavorite,uiDispatcher)
    }

    @Provides
    fun findArtistByMbidProvider(artistRepository: ArtistRepository)
            = FindArtistByMbid(artistRepository)

    @Provides
    fun getPopularAlbumsProvider(albumsRepository: AlbumsRepository)
    = GetPopularAlbums(albumsRepository)

    @Provides
    fun toggleArtistFavoriteProvider(artistRepository: ArtistRepository)
    = ToggleArtistFavorite(artistRepository)
}

@Subcomponent(modules = [DetailActivityModule::class])
interface DetailActivityComponent{
    val detailViewModel: DetailViewModel
}


