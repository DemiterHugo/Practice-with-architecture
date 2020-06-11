package com.example.musicademi.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.musicademi.R
import com.example.musicademi.data.server.Artista
import com.example.musicademi.model.ArtistsRepository
import com.example.musicademi.startActivity

import com.example.musicademi.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val presenter by lazy {MainPresenter(ArtistsRepository(this))}
    private val artistAdapter = ArtistAdapter(){
        presenter.onArtistClicked(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreate(this)
        recyclerArtist.adapter = artistAdapter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun updateData(artists: List<Artista>) {
        artistAdapter.artists = artists
    }

    override fun navigateTo(artista: Artista) {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.ARTIST,artista)
        }
    }

}
