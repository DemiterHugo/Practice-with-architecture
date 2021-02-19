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
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.IllegalStateException

class DetailActivity : ScopeActivity(){

    companion object{
        const val MBID = "DetailActivity:mbidartist"
        const val NAME = "DetailActivity:nameartist"
    }


    private lateinit var albumsAdapter: AlbumsAdapter
    private val viewModel: DetailViewModel by viewModel{
        parametersOf(intent.getStringExtra(MBID))
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


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
