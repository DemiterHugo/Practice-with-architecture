package com.example.musicademi.model.database

import com.demiter.data.source.LocalDataSource
import com.demiter.domain.Album as DomainAlbum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.demiter.domain.Artista as DomainArtist

import com.example.musicademi.model.database.Artista as RoomArtist
import com.example.musicademi.model.database.Album as RoomAlbum

class RoomDataSource(db: MusicDatabase): LocalDataSource {

    private val artistDao = db.artistaDao()


    override suspend fun isEmpty(): Boolean {
        return withContext(Dispatchers.IO) {
            artistDao.artistaCount() <= 0
        }
    }

    override suspend fun isEmptybyName(nameArtista: String): Boolean {
        return withContext(Dispatchers.IO) {
            artistDao.albumCountByName(nameArtista) <= 0
        }
    }

    override suspend fun getPopularArtists(): List<DomainArtist> {
        return withContext(Dispatchers.IO) {
            artistDao.getAllArtista().map { artist -> artist.roomToDomainArtist() }
        }
    }

    override suspend fun getPopularAlbumsByName(nameArtista: String): List<DomainAlbum> {
        return withContext(Dispatchers.IO) {
            artistDao.getAllAlbumsByName(nameArtista).map { album -> album.roomToDomainAlbum() }
        }
    }

    override suspend fun saveArtists(artists: List<DomainArtist>) {
        withContext(Dispatchers.IO) {
            artistDao.insertArtistas(artists.map { domainArtista -> domainArtista.domainToRoomArtist() })
        }
    }

    override suspend fun saveAlbums(abums: List<DomainAlbum>) {
        withContext(Dispatchers.IO) {
            artistDao.insertAlbums(abums.map { album -> album.domainToRoomAlbum() })
        }
    }

    override suspend fun findByMbid(mbid: String): DomainArtist {
        return withContext(Dispatchers.IO) {
            artistDao.findByMbidArtistDb(mbid).roomToDomainArtist()
        }
    }

    override suspend fun update(artist: DomainArtist) {
        withContext(Dispatchers.IO) {
            artistDao.updateArtista(artist.domainToRoomArtist())
        }
    }

//-------------------------------------------------------------------------------------------------------------------

    fun DomainAlbum.domainToRoomAlbum(): RoomAlbum {
        return RoomAlbum(
            idAlbum,
            name,
            playcount,
            url,
            nameArtistaShort,
            mbidArtistaShort,
            urlArtistaShort,
            image
        )
    }

    fun DomainArtist.domainToRoomArtist(): RoomArtist {
        return RoomArtist(
            idArtist,
            name,
            listeners,
            mbid,
            url,
            streamable,
            favorite,
            image
        )
    }


    fun RoomAlbum.roomToDomainAlbum(): DomainAlbum {
        return DomainAlbum(
            idAlbum,
            name,
            playcount,
            url,
            nameArtistaShort,
            mbidArtistaShort,
            urlArtistaShort,
            image
        )
    }


    fun RoomArtist.roomToDomainArtist(): DomainArtist {
        return DomainArtist(
            idArtist,
            name,
            listeners,
            mbid,
            url,
            streamable,
            favorite,
            image
        )
    }
}