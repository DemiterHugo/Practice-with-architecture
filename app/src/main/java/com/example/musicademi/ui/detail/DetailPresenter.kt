package com.example.musicademi.ui.detail

import com.example.musicademi.data.server.Album
import com.example.musicademi.data.server.Artista
import com.example.musicademi.model.AlbumsRepository
import com.example.musicademi.ui.common.Scope
import kotlinx.coroutines.launch

class DetailPresenter(private val albumsRepository: AlbumsRepository) : Scope by Scope.Iml() {

    private var view: View? = null

    interface View{
        fun infoArtist(artista: Artista)
        fun updateAlbum(albums: List<Album>)
    }

    fun onCreate(view: View, parcelableExtra: Artista){
        initScope()
        this.view = view
        view.infoArtist(parcelableExtra)

        launch{
            view.updateAlbum(albumsRepository.findPopularAlbums(parcelableExtra.name).topalbums.albums)
        }
    }

    fun onDestroy() {
        cancelScope()
        view = null
    }
}