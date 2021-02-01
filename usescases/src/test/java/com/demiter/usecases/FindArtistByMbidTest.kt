package com.demiter.usecases

import com.demiter.data.repository.ArtistRepository
import com.demiter.usescases.FindArtistByMbid
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FindArtistByMbidTest {
    @Mock
    lateinit var artistRepository: ArtistRepository

    lateinit var findArtistByMbid: FindArtistByMbid

    @Before
    fun setUp(){
        findArtistByMbid = FindArtistByMbid(artistRepository)
    }

    @Test
    fun `invoke calls artists repository`(){
        runBlocking {
            val artist = mockedArtist.copy(mbid = "555c555")
            whenever(artistRepository.findByMbid("555c555")).thenReturn(artist)

            val result = findArtistByMbid.invoke("555c555")

            assertEquals(artist,result)
        }
    }
}