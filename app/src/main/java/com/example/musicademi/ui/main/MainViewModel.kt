package com.example.musicademi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicademi.model.server.Artista
import com.example.musicademi.model.server.ArtistsRepository
import com.example.musicademi.ui.common.Event
import com.example.musicademi.ui.common.Scope
import kotlinx.coroutines.launch

class MainViewModel(private val artistsRepository: ArtistsRepository) : ViewModel(), Scope by Scope.Iml() {
    init {
        initScope()
    }

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refreshMain()
            return _model
        }
    private val _navigation = MutableLiveData<Event<Artista>>()
    val navigation: LiveData<Event<Artista>>
        get(){
            return _navigation
        }

    sealed class UiModel{
        object Loading: UiModel()
        class Content(val artists: List<Artista>): UiModel()
        object RequestLocationPermission: UiModel()
    }

    private fun refreshMain(){
        _model.value = UiModel.RequestLocationPermission
    }
    fun onCoarsePermissionRequested() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(artistsRepository.findPopularArtists().topartists.artists)
        }
    }

    fun onArtistClicked(artista: Artista) {
        _navigation.value = Event(artista)
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }
}

