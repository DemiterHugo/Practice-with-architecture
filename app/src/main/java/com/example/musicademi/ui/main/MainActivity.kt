package com.example.musicademi.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.musicademi.R
import com.example.musicademi.data.server.Artista
import com.example.musicademi.model.ArtistsRepository
import com.example.musicademi.startActivity

import com.example.musicademi.ui.detail.DetailActivity
import com.example.musicademi.ui.main.MainViewModel.UiModel.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var artistAdapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, MainViewModelFactory(ArtistsRepository(this))).get()
        artistAdapter = ArtistAdapter(viewModel::onArtistClicked)
        recyclerArtist.adapter = artistAdapter
        viewModel.model.observe(this, Observer (::updateUi))
    }

    fun updateUi( uiModel: MainViewModel.UiModel){
        progress.visibility = if (uiModel == Loading) View.VISIBLE else View.GONE
        when(uiModel){
            is Content -> artistAdapter.artists = uiModel.artists
            is Navigation -> startActivity<DetailActivity> {
                putExtra(DetailActivity.ARTIST,uiModel.artist)
            }
        }
    }

}
