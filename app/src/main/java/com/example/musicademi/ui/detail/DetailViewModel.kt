package com.example.musicademi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicademi.model.server.Album
import com.example.musicademi.model.server.Artista
import com.example.musicademi.model.server.AlbumsRepository
import com.example.musicademi.ui.common.Scope
import kotlinx.coroutines.launch

class DetailViewModel(private val albumsRepository: AlbumsRepository, private val artista: Artista) :ViewModel(), Scope by Scope.Iml() {
    init {
        initScope()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
    get() {
        if (_model.value == null) refreshDetail()
        return _model
    }

    sealed class UiModel{
        class Content(val albums:List<Album>): UiModel()
        class TheArtist(val artista: Artista): UiModel()
    }

    fun refreshDetail (){
        launch {
            _model.value = UiModel.TheArtist(artista)
            _model.value=UiModel.Content(albumsRepository.findPopularAlbums(artista.name).topalbums.albums)
        }
    }
}

