package com.demiter.data.repository

import com.demiter.data.source.PermissionChecker.Permission.COARSE_LOCATION
import com.demiter.data.source.LocationDataSource
import com.demiter.data.source.PermissionChecker

class RegionRepository(
    private val locationDataSource: LocationDataSource,
    private val permissionChecker: PermissionChecker){

    companion object{
         const val DEFAULT_REGION = "spain"
    }

    suspend fun findLastRegion(): String{
        return if (permissionChecker.check(COARSE_LOCATION)){
            locationDataSource.findLastRegion() ?: DEFAULT_REGION
                }else{
            DEFAULT_REGION
        }
    }
}

