package com.example.musicademi.model

import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import android.Manifest
import com.demiter.data.repository.PermissionChecker

class AndroidPermissionChecker(private val application: Application): PermissionChecker {

    override suspend fun check(permission: PermissionChecker.Permission): Boolean{
        return ContextCompat.checkSelfPermission(application, permission.toAndroidId()) == PackageManager.PERMISSION_GRANTED
    }

    private fun PermissionChecker.Permission.toAndroidId(): String {
        return when(this){
            PermissionChecker.Permission.COARSE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION       }
    }
}

