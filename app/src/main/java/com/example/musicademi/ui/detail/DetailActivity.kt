package com.example.musicademi.ui.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musicademi.R
import com.example.musicademi.data.server.Artista
import com.example.musicademi.data.server.TheMusicDb
import com.example.musicademi.loadUrl
import com.example.musicademi.ui.common.CoroutineScopeActivity
import com.example.musicademi.ui.main.AlbumsAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.launch

class DetailActivity : CoroutineScopeActivity() {

    companion object{
        const val ARTIST = "DetailActivity:artist"
    }
    private lateinit var artista: String
    private var albumsAdapter = AlbumsAdapter()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.getParcelableExtra<Artista>(ARTIST)?.run {
            artistDetailToolbar.title = this.name
            artista = this.name
            artistDetailImage.loadUrl(this.image[2].text)
        }

        launch{
            val albums = TheMusicDb.service.listTopAlbumsAsync(artista,getString(R.string.apy_key))
            albumsAdapter.albums = albums.topalbums.albums
        }

        recyclerAlbums.adapter = albumsAdapter

    }
}
