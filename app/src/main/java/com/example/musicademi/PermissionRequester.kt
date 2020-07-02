package com.example.musicademi

import android.app.Activity
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener

class PermissionRequester(private val activity: Activity, private val permission:String) {

    fun request(continuation: (Boolean) -> Unit){

        Dexter
            .withActivity(activity)
            .withPermission(permission)
            .withListener(object : BasePermissionListener(){
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    super.onPermissionGranted(response)
                    continuation(true)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    super.onPermissionDenied(response)
                    continuation(false)
                }
            }).check()
    }
}