package com.example.musicademi.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.demiter.data.repository.RegionRepository
import com.demiter.data.repository.ArtistRepository
import com.demiter.usescases.GetPopularArtists
import com.example.musicademi.PermissionRequester
import com.example.musicademi.R
import com.example.musicademi.model.AndroidPermissionChecker
import com.example.musicademi.model.PlayServicesLocationDataSource
import com.example.musicademi.model.database.RoomDataSource
import com.example.musicademi.model.server.ServerDataSource
import com.example.musicademi.ui.common.getViewModel
import com.example.musicademi.ui.common.app
import com.example.musicademi.ui.common.startActivity

import com.example.musicademi.ui.detail.DetailActivity
import com.example.musicademi.ui.main.MainViewModel.UiModel.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ScopeActivity() {

    //private lateinit var viewModel: MainViewModel
    private lateinit var artistAdapter: ArtistAdapter
    private val coarsePermissionRequester = PermissionRequester(this,ACCESS_COARSE_LOCATION)
    //private lateinit var component: MainActivityComponent
    //private val viewModel: MainViewModel by lazy { getViewModel { component.mainViewModel } }
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //component = app.component.plus(MainActivityModule())

         /*viewModel = getViewModel{
             MainViewModel(
                 GetPopularArtists(
                     ArtistRepository(RoomDataSource(app.db),
                                        ServerDataSource(),
                                        RegionRepository(PlayServicesLocationDataSource(app),
                                                            AndroidPermissionChecker(app)),
                                        app.getString(R.string.apy_key)
                     )
                 )
             )
         }*/

        artistAdapter = ArtistAdapter { viewModel.onArtistClicked(it) }
        recyclerArtist.adapter = artistAdapter
        viewModel.model.observe(this, Observer (::updateUi))
        viewModel.navigation.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                startActivity<DetailActivity> {
                    putExtra(DetailActivity.MBID,it.mbid)
                    putExtra(DetailActivity.NAME,it.name)
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
