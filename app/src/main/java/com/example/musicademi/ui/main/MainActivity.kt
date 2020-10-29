package com.example.musicademi.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.musicademi.PermissionRequester
import com.example.musicademi.R
import com.example.musicademi.ui.common.getViewModel
import com.example.musicademi.model.server.ArtistsRepository
import com.example.musicademi.ui.common.app
import com.example.musicademi.ui.common.startActivity

import com.example.musicademi.ui.detail.DetailActivity
import com.example.musicademi.ui.main.MainViewModel.UiModel.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var artistAdapter: ArtistAdapter
    private val coarsePermissionRequester = PermissionRequester(this,ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         viewModel = getViewModel{
             MainViewModel(ArtistsRepository(app))
         }
        artistAdapter = ArtistAdapter(viewModel::onArtistClicked)
        recyclerArtist.adapter = artistAdapter
        viewModel.model.observe(this, Observer (::updateUi))
        viewModel.navigation.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                startActivity<DetailActivity> {
                    putExtra(DetailActivity.ARTIST,it.artista.mbid)
                }
            }
        })
    }

    private fun updateUi(uiModel: MainViewModel.UiModel){
        progress.visibility = if (uiModel == Loading) View.VISIBLE else View.GONE
        when(uiModel){
            is Content -> artistAdapter.artists = uiModel.artists

            RequestLocationPermission -> coarsePermissionRequester.request {
                viewModel.onCoarsePermissionRequested()

            }
        }
    }

}
