package com.example.musicademi.model

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.demiter.data.source.LocationDataSource
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine



class PlayServicesLocationDataSource(application: Application) : LocationDataSource {

    private val geocoder = Geocoder(application)
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    @SuppressLint("MissingPermission")
    override suspend fun findLastRegion(): String? {
        return suspendCancellableCoroutine{ continuation ->
            fusedLocationClient.lastLocation.addOnCompleteListener {
                continuation.resume(it.result.toRegion())
            }
        }
    }

    private fun Location?.toRegion(): String?{
        val addresses = this?.let {
            geocoder.getFromLocation(latitude, longitude, 1)
        }
        return addresses?.firstOrNull()?.countryCode
    }


}