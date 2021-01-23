package com.example.musicademi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demiter.domain.Album
import com.demiter.domain.Artista
import com.demiter.usescases.FindArtistByMbid
import com.demiter.usescases.GetPopularAlbums
import com.demiter.usescases.GetPopularArtists
import com.demiter.usescases.ToggleArtistFavorite
import com.example.musicademi.model.database.AlbumDb
import com.example.musicademi.model.database.ArtistDb
import com.example.musicademi.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class DetailViewModel(
    private val mbidbartista: String,
    private val findArtistByMbid: FindArtistByMbid,
    private val getPopularAlbums: GetPopularAlbums,
    private val toggleArtistFavorite: ToggleArtistFavorite
) :ScopedViewModel() {



    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
    get() {
        if (_model.value == null) refreshDetail()
        return _model
    }

    sealed class UiModel(){
        class Content(val albums:List<Album>): UiModel()
        class TheArtist(val artista: Artista): UiModel()
    }

    private fun refreshDetail (){
        launch {

            _model.value = UiModel.TheArtist(findArtistByMbid.invoke(mbidbartista))
            val l = UiModel.Content(getPopularAlbums.invoke())
            l
            _model.value = l
        }
    }

    fun onFavoriteClicked(){
        launch {
                _model.value = UiModel.TheArtist(toggleArtistFavorite.invoke(findArtistByMbid.invoke(mbidbartista)))
        }
    }

}

    /*override fun onCleared() {
        cancelScope()
        super.onCleared()
    }*/


