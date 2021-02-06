package com.example.musicademi.model.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMusicDb(baseUrl: String) {

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    val service: TheMusicDbService = Retrofit
        .Builder()
        //.baseUrl("http://ws.audioscrobbler.com/2.0/")
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create<TheMusicDbService>(
                TheMusicDbService::class.java)
        }


}