package com.example.musicademi.model

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.location.Geocoder
import android.location.Location
import com.example.musicademi.toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class RegionRepository(activity: Activity) {

    companion object{
        const val DEFAULT_REGION = "spain"
    }

    private val coarsePermissionChecker = PermissionChecker(activity,ACCESS_COARSE_LOCATION)
    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)
    private val geocoder = Geocoder(activity)


    suspend fun findLastRegion(): String = findLastLocation().toRegion()

        private suspend fun findLastLocation(): Location?{
            val success = coarsePermissionChecker.request()
            return if(success) lastLocationSuspended() else null
        }

        @SuppressLint("MissingPermission")
        private suspend fun lastLocationSuspended(): Location? {
            return suspendCancellableCoroutine { continuation ->
                fusedLocationClient.lastLocation.addOnCompleteListener {
                    continuation.resume(it.result)
                }
            }
        }

        private fun Location?.toRegion(): String{
            val addresses = this?.let {
                geocoder.getFromLocation(it.latitude,it.longitude,1)
            }
            return addresses?.firstOrNull()?.countryCode?: DEFAULT_REGION
        }
}