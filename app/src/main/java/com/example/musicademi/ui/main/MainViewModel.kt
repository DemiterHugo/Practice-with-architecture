package com.example.musicademi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicademi.data.server.Artista
import com.example.musicademi.model.ArtistsRepository
import com.example.musicademi.startActivity
import com.example.musicademi.ui.common.Scope
import com.example.musicademi.ui.detail.DetailActivity
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

    sealed class UiModel{
        object Loading: UiModel()
        class Content(val artists: List<Artista>): UiModel()
        class Navigation(val artist: Artista): UiModel()
    }


    private fun refreshMain() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(artistsRepository.findPopularArtists().topartists.artists)
        }
    }

    fun onArtistClicked(artista: Artista) {
        _model.value = UiModel.Navigation(artista)
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val artistsRepository: ArtistsRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(artistsRepository) as T
    }
}