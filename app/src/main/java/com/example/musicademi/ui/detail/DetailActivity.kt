package com.example.musicademi.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicademi.R
import com.example.musicademi.data.server.Album
import com.example.musicademi.data.server.Artista
import com.example.musicademi.loadUrl
import com.example.musicademi.model.AlbumsRepository
import com.example.musicademi.ui.main.AlbumsAdapter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailPresenter.View{


    companion object{
        const val ARTIST = "DetailActivity:artist"
    }
    private lateinit var artista: Artista
    private var albumsAdapter = AlbumsAdapter()
    private val presenter by lazy { DetailPresenter(AlbumsRepository(this)) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        recyclerAlbums.adapter = albumsAdapter
        artista = intent.getParcelableExtra(ARTIST) ?: throw (IllegalStateException("Movie not found"))
        presenter.onCreate(this, artista )
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun infoArtist(artista: Artista) {
        artista?.run {
            artistDetailToolbar.title = this.name
            artistDetailImage.loadUrl(this.image[2].text)
            artistDetailInfo.setArtist(this)
        }
    }

    override fun updateAlbum(albums: List<Album>) {
        albumsAdapter.albums = albums
    }
}
