package com.demiter.usecases

import com.demiter.data.repository.ArtistRepository
import com.demiter.lib.mockedArtist
import com.demiter.usescases.GetPopularArtists
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetPopularArtistTest {

    @Mock
    lateinit var artistRepository: ArtistRepository

    lateinit var getPopularArtists: GetPopularArtists

    @Before
    fun setUp(){
        getPopularArtists = GetPopularArtists(artistRepository)
    }

    @Test
    fun `invoke calls artists repository`(){
        runBlocking {
            val artists = listOf(mockedArtist.copy(1))
            whenever(artistRepository.getPopularArtists()).thenReturn(artists)

            val result = getPopularArtists.invoke()

            assertEquals(artists,result)
        }
    }
}