package com.example.musicademi.ui


import com.demiter.data.repository.AlbumsRepository
import com.demiter.data.repository.ArtistRepository
import com.demiter.data.repository.RegionRepository
import com.demiter.data.source.LocalDataSource
import com.demiter.data.source.LocationDataSource
import com.demiter.data.source.PermissionChecker
import com.demiter.data.source.RemoteDataSource
import com.demiter.domain.Album
import com.demiter.domain.Artista
import com.demiter.lib.mockedAlbum
import com.demiter.lib.mockedArtist
import com.example.musicademi.di.DataModule
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val mockedAppModule = module {
    single(named("apikey")){"123456"}
    single<LocalDataSource> {FakeLocalDataSource()}
    single<RemoteDataSource> {FakeRemoteDataSource()}
    single<LocationDataSource>{FakeLocationDataSource()}
    single<PermissionChecker>{FakePermissionChecker()}
    single{Dispatchers.Unconfined}
}

private val dataModule = module {
    factory { RegionRepository(get(),get())}
    factory { ArtistRepository(get(),get(),get(),get(named("apikey"))) }
    factory { AlbumsRepository(get(),get(),get(),get(named("apikey"))) }
}

val defaultFakeArtists = listOf(
    mockedArtist.copy(1),mockedArtist.copy(2),mockedArtist.copy(3),
    mockedArtist.copy(4),mockedArtist.copy(5)
)

val defaultFakeAlbums = listOf(
    mockedAlbum.copy(1),mockedAlbum.copy(2),mockedAlbum.copy(3),
    mockedAlbum.copy(4),mockedAlbum.copy(5)
)

fun initMockedDi(vararg modules: Module){
    startKoin {
        modules(listOf(mockedAppModule,dataModule)+modules)
    }
}

class FakeLocalDataSource: LocalDataSource{

    var artists: List<Artista> = emptyList()
    var albums: List<Album> = emptyList()

    override suspend fun isEmpty(): Boolean {
        return artists.isEmpty()
    }

    override suspend fun isEmptybyName(nameArtista: String): Boolean {
        return albums.filter { it.nameArtistaShort==nameArtista }.isEmpty()
    }

    override suspend fun getPopularArtists(): List<Artista> {
        return artists
    }

    override suspend fun getPopularAlbumsByName(nameArtista: String): List<Album> {
        return  albums.filter {it.nameArtistaShort == nameArtista  }
    }

    override suspend fun saveArtists(artists: List<Artista>) {
        this.artists = artists
    }

    override suspend fun saveAlbums(albums: List<Album>) {
        this.albums = albums
    }

    override suspend fun findByMbid(mbid: String): Artista {
        return artists.first { it.mbid== mbid }
    }

    override suspend fun update(artist: Artista) {
        artists = artists.filterNot { it.idArtist == artist.idArtist }+artist
    }
}

class FakeRemoteDataSource: RemoteDataSource{

    var artists = defaultFakeArtists
    var albums = defaultFakeAlbums

    override suspend fun getPopularArtists(apiKey: String, region: String): List<Artista> {
        return artists
    }

    override suspend fun getPopularAlbums(name: String, apikey: String): List<Album> {
        return albums
    }
}

class FakeLocationDataSource: LocationDataSource{

    var location ="spain"

    override suspend fun findLastRegion(): String? {
        return location
    }
}

class FakePermissionChecker: PermissionChecker {

    var permissionGranted = true

    override suspend fun check(permission: PermissionChecker.Permission): Boolean {
        return permissionGranted
    }
}