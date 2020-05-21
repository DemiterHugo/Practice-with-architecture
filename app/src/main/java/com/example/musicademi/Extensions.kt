package com.example.musicademi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachRoot: Boolean=true): View =
    LayoutInflater.from(context).inflate(layoutRes,this, attachRoot)

fun ImageView.loadUrl(url: String){
    Glide.with(context).load(url).into(this)
}