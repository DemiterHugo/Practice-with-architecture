package com.example.musicademi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicademi.model.database.AlbumDb
import com.example.musicademi.model.database.ArtistDb
import com.example.musicademi.model.server.Album
import com.example.musicademi.model.database.Artista
import com.example.musicademi.model.server.AlbumsRepository
import com.example.musicademi.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class DetailViewModel(private val albumsRepository: AlbumsRepository, private val Mbidbartista: String) :ScopedViewModel() {

    private lateinit var artistaDb: ArtistDb

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
    get() {
        if (_model.value == null) refreshDetail()
        return _model
    }

    sealed class UiModel(){
        class Content(val albums:List<AlbumDb>): UiModel()
        class TheArtist(val artistaDb: ArtistDb): UiModel()
    }

    private fun refreshDetail (){
        launch {
            artistaDb = albumsRepository.findByMbidArtistDb(Mbidbartista)
            _model.value = UiModel.TheArtist(artistaDb)
            _model.value=UiModel.Content(
                albumsRepository.findPopularAlbums(artistaDb.artista.name))
        }
    }

    fun onFavoriteClicked(){
        launch {
            artistaDb.let {
                val updateArtist =it.artista.copy(favorite = !it.artista.favorite)
                albumsRepository.updateArtist(updateArtist)
                _model.value = UiModel.TheArtist(albumsRepository.findByMbidArtistDb(Mbidbartista))
            }
        }

    }

    /*override fun onCleared() {
        cancelScope()
        super.onCleared()
    }*/
}

