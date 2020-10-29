package com.example.musicademi.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.musicademi.R
import com.example.musicademi.model.server.Artista
import com.example.musicademi.ui.common.getViewModel
import com.example.musicademi.ui.common.loadUrl
import com.example.musicademi.model.server.AlbumsRepository
import com.example.musicademi.ui.common.app
import com.example.musicademi.ui.main.AlbumsAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.IllegalStateException

class DetailActivity : AppCompatActivity(){

    companion object{
        const val ARTIST = "DetailActivity:artist"
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var albumsAdapter: AlbumsAdapter


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel = getViewModel {
            DetailViewModel(AlbumsRepository(app),intent.getStringExtra(ARTIST) ?: throw (IllegalStateException("id not found")))
        }
        albumsAdapter = AlbumsAdapter()
        recyclerAlbums.adapter = albumsAdapter
        viewModel.model.observe(this, Observer(::updateUi))
        artistDetailFavorite.setOnClickListener { viewModel.onFavoriteClicked() }
    }

    private fun updateUi(uiModel: DetailViewModel.UiModel) {
        when(uiModel){
            is DetailViewModel.UiModel.TheArtist ->
                with(uiModel.artistaDb){
                artistDetailToolbar.title = this.artista.name
                artistDetailImage.loadUrl(this.imageArtist.get(2).text)
                artistDetailInfo.setArtist(this)
                    artistDetailFavorite.setImageDrawable(getDrawable(if (this.artista.favorite)R.drawable.ic_favorite_on else R.drawable.ic_favorite_off))
            }
            is DetailViewModel.UiModel.Content -> albumsAdapter.albums = uiModel.albums
        }
    }
}
