package com.example.musicademi.model

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.example.musicademi.toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class RegionRepository(application: Application) {

    companion object{
        const val DEFAULT_REGION = "spain"
    }

    private val coarsePermissionChecker = PermissionChecker(application, ACCESS_COARSE_LOCATION)
    private val locationDataSource = PlayServicesLocationDataSource(application)
    private val geocoder = Geocoder(application)


    suspend fun findLastRegion(): String = findLastLocation().toRegion()

        private suspend fun findLastLocation(): Location?{
            val success = coarsePermissionChecker.check()
            return if(success) locationDataSource.findLastLocation() else null
        }



        private fun Location?.toRegion(): String{
            val addresses = this?.let {
                geocoder.getFromLocation(it.latitude,it.longitude,1)
            }
            return addresses?.firstOrNull()?.countryCode?: DEFAULT_REGION
        }
}