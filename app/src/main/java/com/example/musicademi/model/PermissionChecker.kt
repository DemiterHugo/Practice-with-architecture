package com.example.musicademi.model

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PermissionChecker(private val application: Application, private val permission: String) {



    fun check(): Boolean{
        return ContextCompat.checkSelfPermission(application, permission) == PackageManager.PERMISSION_GRANTED
    }
}