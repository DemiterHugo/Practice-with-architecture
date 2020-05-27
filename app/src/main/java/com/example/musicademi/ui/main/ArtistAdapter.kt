package com.example.musicademi.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.musicademi.*
import com.example.musicademi.data.server.Artista
import kotlinx.android.synthetic.main.view_artist.view.*
import kotlin.properties.Delegates

class ArtistAdapter(private var listener: (Artista) -> Unit): RecyclerView.Adapter<ArtistAdapter.MyViewHolder>() {

    var artists: List<Artista> by basicDiffUtil(
            emptyList(),
            areItemsTheSame = {old, new -> old.mbid == new.mbid } )

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
        private lateinit var image: String

        fun bind(artist: Artista){
            itemView.artistTitle.text = artist.name
            image = ImageArtist(artist.name).imageArtist()
            artist.image[2].text = image
            itemView.artistCover.loadUrl(artist.image[2].text)
        }
    }
}

