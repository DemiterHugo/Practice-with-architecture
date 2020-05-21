package com.example.musicademi.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicademi.R
import com.example.musicademi.data.server.TheMusicDb
import com.example.musicademi.toast
import com.example.musicademi.ui.common.CoroutineScopeActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : CoroutineScopeActivity() {

    private val adapter = ArtistAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = adapter

        launch {
            val artists = TheMusicDb.service.listTopArtistsAsync(getString(R.string.apy_key))
            adapter.artists = artists.topartists.artists
        }

    }
}
