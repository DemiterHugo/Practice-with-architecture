package com.example.musicademi.model

import android.Manifest
import android.app.Activity
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PermissionChecker(private val activity: Activity, private val permission: String) {



    suspend fun request(): Boolean{
        return suspendCancellableCoroutine { continuation ->
            Dexter
                .withActivity(activity)
                .withPermission(permission)
                .withListener(object: BasePermissionListener(){
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        super.onPermissionGranted(response)
                        continuation.resume(true)
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        super.onPermissionDenied(response)
                        continuation.resume(false)
                    }
                }).check()
        }
    }
}