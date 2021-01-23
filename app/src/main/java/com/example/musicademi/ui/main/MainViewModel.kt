package com.example.musicademi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demiter.usescases.GetPopularArtists
import com.example.musicademi.model.database.ArtistDb
import com.example.musicademi.ui.common.Event
import com.example.musicademi.ui.common.ScopedViewModel
import kotlinx.coroutines.launch
import com.demiter.domain.Artista
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel(private val getPopularArtists: GetPopularArtists): ScopedViewModel() {


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

    init {
        initScope()
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
                _model.value = UiModel.Content(getPopularArtists.invoke())
        }
    }

    fun onArtistClicked(artista: Artista) {
        _navigation.value = Event(artista)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}

