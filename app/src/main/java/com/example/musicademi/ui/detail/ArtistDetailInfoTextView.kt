package com.example.musicademi.ui.detail

import android.content.Context
import android.util.AttributeSet
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.demiter.domain.Artista


class ArtistDetailInfoTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {



        fun setArtist(artist: Artista) {
            with(artist) {
                text = buildSpannedString {

                    bold { append("Name: ") }
                    appendLine(name)

                    bold { append("Listeners: ") }
                    appendLine(listeners)

                    bold { append("Url: ") }
                    appendLine(url)

                    bold { append("Streamable: ") }
                    appendLine(streamable)
                }
            }
        }

}
