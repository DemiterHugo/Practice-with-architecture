package com.demiter.data.repository

import com.demiter.data.repository.PermissionChecker.Permission.COARSE_LOCATION
import com.demiter.data.source.LocationDataSource

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

interface PermissionChecker{

    enum class Permission{ COARSE_LOCATION }

    suspend fun check(permission: Permission): Boolean
}