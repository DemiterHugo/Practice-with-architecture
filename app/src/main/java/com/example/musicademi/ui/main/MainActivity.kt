package com.example.musicademi.ui.main

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicademi.R
import com.example.musicademi.data.server.TheMusicDb
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

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val adapter = ArtistAdapter(){
        startActivity<DetailActivity> {
            putExtra(DetailActivity.ARTIST,it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        launch {
            val location = getLocation()
            val artists = TheMusicDb.service.listTopArtistsAsync(getRegionFromLocation(location), getString(R.string.apy_key))
            adapter.artists = artists.topartists.artists
            textLocation.text = artists.topartists.attr.country
        }
        recyclerArtist.adapter = adapter
    }

    private suspend fun getLocation(): Location? {
        val success = requestCoarseLocationPermission()
        toast(success.toString())
        return if (success) findLastLocation() else null
    }

    private suspend fun findLastLocation(): Location? =
        suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation
                .addOnCompleteListener {
                    continuation.resume(it.result)

                }
        }

    private suspend fun requestCoarseLocationPermission(): Boolean {
        return suspendCancellableCoroutine { continuation ->
            Dexter
                .withActivity(this@MainActivity)
                .withPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(object : BasePermissionListener() {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        super.onPermissionGranted(response)
                        continuation.resume(true)
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        continuation.resume(false)
                    }
                }).check()
        }
    }

    private fun getRegionFromLocation(location: Location?): String {
        val geocoder = Geocoder(this@MainActivity)
        val fromLocation = location?.let{
            geocoder.getFromLocation(location.latitude, location.longitude, 1)
        }
        return fromLocation?.firstOrNull()?.countryCode?.toLowerCase()?: "spain"
    }


}
