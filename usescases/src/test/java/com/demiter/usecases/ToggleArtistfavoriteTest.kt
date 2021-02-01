package com.demiter.usecases

import com.demiter.data.repository.ArtistRepository
import com.demiter.usescases.ToggleArtistFavorite
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ToggleArtistfavoriteTest {
    @Mock
    lateinit var artistRepository: ArtistRepository

    lateinit var toggleArtistFavorite: ToggleArtistFavorite

    @Before
    fun setUp(){
        toggleArtistFavorite = ToggleArtistFavorite(artistRepository)
    }

    @Test
    fun `invoke calls artists repository`(){
        runBlocking {
            val artist = mockedArtist.copy(1)

            val result = toggleArtistFavorite.invoke(artist)

            verify(artistRepository).update(result)
        }
    }

    @Test
    fun `favorite artist becomes unfavorite`(){
        runBlocking {
            val artist = mockedArtist.copy(favorite = true)

            val result = toggleArtistFavorite.invoke(artist)

            assertFalse(result.favorite)
        }
    }

    @Test
    fun `unfavorite artist becomes favorite`(){
        runBlocking {
            val artist = mockedArtist.copy(favorite = false)
            val result = toggleArtistFavorite.invoke(artist)
            assertTrue(result.favorite)
        }
    }
}