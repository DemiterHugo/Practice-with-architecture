package com.example.musicademi.ui.main

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicademi.R
import com.example.musicademi.data.server.TheMusicDb
import com.example.musicademi.model.AlbumsRepository
import com.example.musicademi.model.ArtistsRepository
import com.example.musicademi.toast
import com.example.musicademi.startActivity

import com.example.musicademi.ui.common.CoroutineScopeActivity
import com.example.musicademi.ui.detail.DetailActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener
import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.jar.Manifest
import kotlin.coroutines.resume


class MainActivity : CoroutineScopeActivity() {

    private val artistsRepository: ArtistsRepository by lazy { ArtistsRepository(this) }
    private val artistAdapter = ArtistAdapter(){
        startActivity<DetailActivity> {
            putExtra(DetailActivity.ARTIST,it)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        launch {
            artistAdapter.artists = artistsRepository.findPopularArtists().topartists.artists
        }
        recyclerArtist.adapter = artistAdapter
    }

}
