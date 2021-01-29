package com.example.musicademi.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.demiter.data.repository.AlbumsRepository
import com.demiter.data.repository.ArtistRepository
import com.demiter.data.repository.RegionRepository
import com.demiter.usescases.FindArtistByMbid
import com.demiter.usescases.GetPopularAlbums
import com.demiter.usescases.ToggleArtistFavorite
import com.example.musicademi.R
import com.example.musicademi.model.AndroidPermissionChecker
import com.example.musicademi.model.PlayServicesLocationDataSource
import com.example.musicademi.model.database.RoomDataSource
import com.example.musicademi.model.server.ServerDataSource
import com.example.musicademi.ui.common.app
import com.example.musicademi.ui.common.getViewModel
import com.example.musicademi.ui.common.loadUrl
import com.example.musicademi.ui.main.AlbumsAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.IllegalStateException

class DetailActivity : AppCompatActivity(){

    companion object{
        const val MBID = "DetailActivity:mbidartist"
        const val NAME = "DetailActivity:nameartist"

    }

    //private lateinit var viewModel: DetailViewModel
    private lateinit var albumsAdapter: AlbumsAdapter
    private lateinit var component1: DetailActivityComponent
    private val viewModel by lazy { getViewModel { component1.detailViewModel } }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val mbidArtista = intent.getStringExtra(MBID) ?: throw (IllegalStateException("mbid not found"))
        val name = intent.getStringExtra(NAME)?: throw (IllegalStateException("name not found"))

        component1 = app.component.plus(DetailActivityModule(mbidArtista,name))


        /*val artistRepository = ArtistRepository(
            RoomDataSource(app.db),
            ServerDataSource(),
            RegionRepository(PlayServicesLocationDataSource(app),AndroidPermissionChecker(app)),
            app.getString(R.string.apy_key)
        )
        val albumsRepository = AlbumsRepository(
            RoomDataSource(app.db),
            ServerDataSource(),
            name,
            app.getString(R.string.apy_key)
        )

        viewModel = getViewModel {
            DetailViewModel(
                mbidArtista,
                FindArtistByMbid(artistRepository),
                GetPopularAlbums(albumsRepository),
                ToggleArtistFavorite(artistRepository)
            )
        }*/
        albumsAdapter = AlbumsAdapter()
        recyclerAlbums.adapter = albumsAdapter
        viewModel.model.observe(this, Observer(::updateUi))
        artistDetailFavorite.setOnClickListener { viewModel.onFavoriteClicked() }
    }

    private fun updateUi(uiModel: DetailViewModel.UiModel) {

        when(uiModel){
            is DetailViewModel.UiModel.TheArtist ->
                with(uiModel.artista){
                artistDetailToolbar.title = this.name
                artistDetailImage.loadUrl(this.image)
                artistDetailInfo.setArtist(this)

                    val icon = if (this.favorite)R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
                    artistDetailFavorite.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity,icon))
            }

            is DetailViewModel.UiModel.Content -> {

                albumsAdapter.albums = uiModel.albums
            }
        }
    }
}
