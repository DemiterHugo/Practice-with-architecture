package com.example.musicademi.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demiter.domain.Album
import com.demiter.domain.Artista
import com.demiter.usescases.FindArtistByMbid
import com.demiter.usescases.GetPopularAlbums
import com.demiter.usescases.GetPopularArtists
import com.demiter.usescases.ToggleArtistFavorite
import com.example.musicademi.model.database.Album as RoomAlbum
import com.example.musicademi.model.database.Artista as RoomArtist
import com.example.musicademi.ui.common.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailViewModel(
    private val mbidbartista: String,
    private val findArtistByMbid: FindArtistByMbid,
    private val getPopularAlbums: GetPopularAlbums,
    private val toggleArtistFavorite: ToggleArtistFavorite,
    override val uiDispatcher: CoroutineDispatcher
) :ScopedViewModel(uiDispatcher) {


    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
    get() {
        if (_model.value == null) refreshDetail()
        return _model
    }

    sealed class UiModel(){
        data class Content(val albums:List<Album>): UiModel()
        data class TheArtist(val artista: Artista): UiModel()
    }

    private fun refreshDetail (){
        launch {
            _model.value = UiModel.TheArtist(findArtistByMbid.invoke(mbidbartista))
            _model.value = UiModel.Content(getPopularAlbums.invoke())
        }
    }

    fun onFavoriteClicked()=launch {
            var art = toggleArtistFavorite.invoke(findArtistByMbid.invoke(mbidbartista))
            _model.value = UiModel.TheArtist(art)
    }
}




