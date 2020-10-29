package com.example.musicademi.model.server

import com.example.musicademi.MusicApp
import com.example.musicademi.R
import com.example.musicademi.model.database.*
import com.example.musicademi.ui.common.Scope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.musicademi.model.server.Album as AlbumServer
import com.example.musicademi.model.server.Image as ImageServer



class AlbumsRepository(musicApp: MusicApp) {

    private val apiKey = musicApp.getString(R.string.apy_key)
    private val db = musicApp.db


    suspend fun findPopularAlbums(name: String):List<AlbumDb>{
        return withContext(Dispatchers.IO){
            with(db.artistaDao()){
                if (albumCount() <= 0){
                    val albumsServer =TheMusicDb.service.listTopAlbumsAsync(name,apiKey).topalbums.albums
                    insertAlbums(albumsServer.map { album -> convertToAlbumDb(album) })
                }
                getAllAlbums()
            }
        }
    }

    private fun convertToAlbumDb(albumServer: AlbumServer):Album{

        with(albumServer){
            this.image.forEach {
                db.artistaDao().insertImages(Image(0, this.name, it.text, it.size))
            }
            return Album(0,name,playcount,name,url,artist.name, artist.mbid, artist.url)
        }
    }

    suspend fun findByMbidArtistDb(mbid: String): ArtistDb {
        return withContext(Dispatchers.IO){
            db.artistaDao().findByMbidArtistDb(mbid)
        }
    }

}