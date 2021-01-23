package com.example.musicademi.model.database

import com.demiter.data.source.LocalDataSource
import com.demiter.domain.Album
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.demiter.domain.Artista as DomainArtist
import com.demiter.domain.Image as DomainImage
import com.example.musicademi.model.database.Artista as RoomArtist
import com.example.musicademi.model.database.Album as RoomAlbum

class RoomDataSource(db: MusicDatabase): LocalDataSource {

    private val artistDao = db.artistaDao()


    override suspend fun isEmpty(): Boolean {
        return withContext(Dispatchers.IO){
            artistDao.artistaCount() <= 0
        }
    }

    override suspend fun isEmptybyName(nameArtista: String): Boolean {
        return withContext(Dispatchers.IO){
            artistDao.albumCountByName(nameArtista) <= 0
        }
    }

    override suspend fun getPopularArtists(): List<DomainArtist> {
        return withContext(Dispatchers.IO){

             artistDao.getAllArtista().map { artistDb -> artistDb.toDomainArtist() }

        }
    }

    override suspend fun getPopularAlbumsByName(nameArtist: String): List<Album> {
        return withContext(Dispatchers.IO){
            artistDao.getAllAlbumsByName(nameArtist).map { albumDb -> albumDb.toDomainAlbum() }
        }
    }

    override suspend fun saveArtists(artists: List<DomainArtist>) {
         withContext(Dispatchers.IO){
            artistDao.insertArtistas(artists.map { domainArtista ->  domainArtista.toRoomArtist() })
             //var r = artistDao.getAllImages()
             //r.forEach { println(it) }
        }
    }

    override suspend fun saveAlbums(abums: List<Album>) {
         withContext(Dispatchers.IO){
            artistDao.insertAlbums(abums.map{ album -> album.toRoomAlbum() })
        }
    }

    override suspend fun findByMbid(mbid: String): DomainArtist {
        return withContext(Dispatchers.IO){
            artistDao.findByMbidArtistDb(mbid).toDomainArtist()
        }
    }

    override suspend fun update(artist: DomainArtist) {
        withContext(Dispatchers.IO){
            artistDao.updateArtista(artist.toRoomArtist())
        }
    }

//-------------------------------------------------------------------------------------------------------------------

    fun Album.toRoomAlbum(): RoomAlbum{
        with(this){
            image.forEach {
                artistDao.insertImages(Image(creatorId = it.creatorId, text = it.text, size = it.size))
            }
            return RoomAlbum( name=name, playcount=playcount, url=url, nameArtistaShort=nameArtistaShort, mbidArtistaShort=mbidArtistaShort, urlArtistaShort=urlArtistaShort)
        }
    }

    fun DomainArtist.toRoomArtist(): RoomArtist {
        with(this) {
            this.image.forEach {
                artistDao.insertImages(Image(creatorId = it.creatorId, text = it.text,size = it.size))
            }
            return RoomArtist(name=name, listeners = listeners,mbid =  mbid,url =  url,streamable =  streamable,favorite =  favorite)
        }
    }

    fun AlbumDb.toDomainAlbum(): Album{
        val images: MutableList<DomainImage> = mutableListOf()

        with(this) {
            imageAlbumDb.forEach {
                images.add(DomainImage(it.idImage, it.creatorId, it.text, it.size))
            }
        }
        with(this.album){
            return Album(idAlbum,name,playcount, url, nameArtistaShort, mbidArtistaShort, urlArtistaShort, images)
        }
    }

    fun ArtistDb.toDomainArtist():DomainArtist{
        val images: MutableList<DomainImage> = mutableListOf()

        with(this){
            imageArtist.forEach {
                images.add(DomainImage(it.idImage,it.creatorId,it.text,it.size))
            }
        }
        with(this.artista) {
            return DomainArtist(idArtist,name,listeners,mbid,url,streamable,favorite,images)
        }
    }



}