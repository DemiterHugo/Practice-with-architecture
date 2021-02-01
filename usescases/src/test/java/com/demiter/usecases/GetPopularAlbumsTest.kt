package com.demiter.usecases

import com.demiter.data.repository.AlbumsRepository
import com.demiter.usescases.GetPopularAlbums
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPopularAlbumsTest {

    @Mock
    lateinit var albumRepository: AlbumsRepository

    lateinit var getPopularAlbums: GetPopularAlbums

    @Before
    fun setUp(){
        getPopularAlbums = GetPopularAlbums(albumRepository)
    }

    @Test
    fun `invike calls albums repository`(){
        runBlocking {
            val album = listOf(mockedAlbum.copy(1))
            whenever(albumRepository.getPopularAlbums()).thenReturn(album)

            val result = getPopularAlbums.invoke()

            assertEquals(album,result)
        }
    }

}