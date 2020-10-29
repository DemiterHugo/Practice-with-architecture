package com.example.musicademi.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicademi.*
import com.example.musicademi.model.database.ArtistDb
import com.example.musicademi.model.server.Artist
import com.example.musicademi.model.server.Artista
import com.example.musicademi.ui.common.basicDiffUtil
import com.example.musicademi.ui.common.inflate
import com.example.musicademi.ui.common.loadUrl
import kotlinx.android.synthetic.main.view_artist.view.*

class ArtistAdapter(private var listener: (ArtistDb) -> Unit): RecyclerView.Adapter<ArtistAdapter.MyViewHolder>() {

    var artists: List<ArtistDb> by basicDiffUtil(
            emptyList(),
            areItemsTheSamee = {old, new -> old.artista.mbid == new.artista.mbid } )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = parent.inflate(R.layout.view_artist,false)
        return  MyViewHolder(view)
    }

    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(artists[position])
        holder.itemView.setOnClickListener { listener(artists[position]) }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(artist: ArtistDb){
            itemView.artistTitle.text = artist.artista.name
            itemView.artistCover.loadUrl(artist.imageArtist[1].text)
        }
    }
}

