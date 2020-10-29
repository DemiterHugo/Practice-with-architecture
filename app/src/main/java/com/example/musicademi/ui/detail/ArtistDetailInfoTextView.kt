package com.example.musicademi.ui.detail

import android.content.Context
import android.util.AttributeSet
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.example.musicademi.model.database.ArtistDb
import com.example.musicademi.model.server.Artista



class ArtistDetailInfoTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {



        fun setArtist(artist: ArtistDb) {
            with(artist) {
                text = buildSpannedString {

                    bold { append("Name: ") }
                    appendLine(artista.name)

                    bold { append("Listeners: ") }
                    appendLine(artista.listeners)

                    bold { append("Url: ") }
                    appendLine(artista.url)

                    bold { append("Streamable: ") }
                    appendLine(artista.streamable)
                }
            }
        }

}
