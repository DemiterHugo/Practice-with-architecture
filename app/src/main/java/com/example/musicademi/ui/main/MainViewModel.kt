package com.example.musicademi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicademi.model.database.ArtistDb
import com.example.musicademi.model.database.Artista
import com.example.musicademi.model.server.ArtistsRepository
import com.example.musicademi.ui.common.Event
import com.example.musicademi.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val artistsRepository: ArtistsRepository): ScopedViewModel() {


    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refreshMain()
            return _model
        }
    private val _navigation = MutableLiveData<Event<ArtistDb>>()
    val navigation: LiveData<Event<ArtistDb>>
        get(){
            return _navigation
        }

    sealed class UiModel{
        object Loading: UiModel()
        class Content(val artists: List<ArtistDb>): UiModel()
        object RequestLocationPermission: UiModel()
    }

    private fun refreshMain(){
        _model.value = UiModel.RequestLocationPermission
    }
    fun onCoarsePermissionRequested() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(artistsRepository.findPopularArtists())
        }
    }

    fun onArtistClicked(artista: ArtistDb) {
        _navigation.value = Event(artista)
    }

    /*override fun onCleared() {
        cancelScope()
        super.onCleared()
    }*/
}

