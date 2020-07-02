package com.example.musicademi.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.musicademi.R
import com.example.musicademi.data.server.Album
import com.example.musicademi.data.server.Artista
import com.example.musicademi.getViewModel
import com.example.musicademi.loadUrl
import com.example.musicademi.model.AlbumsRepository
import com.example.musicademi.ui.main.AlbumsAdapter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(){

    companion object{
        const val ARTIST = "DetailActivity:artist"
    }

    private lateinit var artista: Artista
    private lateinit var viewModel: DetailViewModel
    private var albumsAdapter = AlbumsAdapter()


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        artista = intent.getParcelableExtra(ARTIST) ?: throw (IllegalStateException("Movie not found"))
        viewModel = getViewModel { DetailViewModel(AlbumsRepository(application),artista) }
        recyclerAlbums.adapter = albumsAdapter
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(uiModel: DetailViewModel.UiModel) {
        when(uiModel){
            is DetailViewModel.UiModel.TheArtist -> with(uiModel.artista){
                artistDetailToolbar.title = this.name
                artistDetailImage.loadUrl(this.image[2].text)
                artistDetailInfo.setArtist(this)
            }
            is DetailViewModel.UiModel.Content -> albumsAdapter.albums = uiModel.albums
        }
    }
}
