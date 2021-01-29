package com.example.musicademi.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demiter.domain.Album
import com.example.musicademi.R
import com.example.musicademi.ui.common.basicDiffUtil
import com.example.musicademi.ui.common.inflate
import com.example.musicademi.ui.common.loadUrl
import kotlinx.android.synthetic.main.view_album.view.*
import kotlin.properties.Delegates

class AlbumsAdapter: RecyclerView.Adapter<AlbumsAdapter.MyViewHolder>() {

    /*var albums: List<AlbumDb> by Delegates.observable(emptyList()){ _, old, new ->
        DiffUtil.calculateDiff(object: DiffUtil.Callback(){
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].album.mbid == new[newItemPosition].album.mbid

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]
        }).dispatchUpdatesTo(this)
    }*/

    var albums: List<Album> by basicDiffUtil(
        emptyList(), areItemsTheSamee = {old, new, -> old.name == new.name}
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = parent.inflate(R.layout.view_album,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album){
            itemView.albumTitle.text = album.name
            itemView.albumCover.loadUrl(album.image)
        }

    }
}