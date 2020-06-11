package com.example.musicademi.ui.main

import com.example.musicademi.data.server.Artista
import com.example.musicademi.model.ArtistsRepository
import com.example.musicademi.startActivity
import com.example.musicademi.ui.common.Scope
import com.example.musicademi.ui.detail.DetailActivity
import kotlinx.coroutines.launch

class MainPresenter(private val artistsRepository: ArtistsRepository) : Scope by Scope.Iml() {


    private var view: View? = null

    interface View{
        fun showProgress()
        fun hideProgress()
        fun updateData(artists: List<Artista>)
        fun navigateTo(artista: Artista)
    }

    fun onCreate(view: View) {
        initScope()
        this.view = view
        launch {
            view.showProgress()
            view.updateData( artistsRepository.findPopularArtists().topartists.artists)
            view.hideProgress()
        }
    }

    fun onDestroy() {
        cancelScope()
        view = null
    }

    fun onArtistClicked(artista: Artista) {
        view?.navigateTo(artista)
    }
}