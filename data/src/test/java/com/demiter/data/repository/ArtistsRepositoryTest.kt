package com.demiter.data.repository

import com.demiter.data.source.LocalDataSource
import com.demiter.data.source.RemoteDataSource
import com.demiter.lib.mockedArtist
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ArtistsRepositoryTest {
    @Mock
    lateinit var localDataSource: LocalDataSource
    @Mock
    lateinit var remoteDataSource: RemoteDataSource
    @Mock
    lateinit var regionRepository: RegionRepository

    lateinit var artistRepository: ArtistRepository
    private val apiKey = "r4r4r4r4fefe"


    @Before
    fun setUp(){
        artistRepository = ArtistRepository(localDataSource, remoteDataSource,regionRepository, apiKey)
    }

    @Test
    fun `getPopularArtists gets from local data source first`(){
        runBlocking {
            val localArtists = listOf(mockedArtist.copy(1))

            whenever(localDataSource.isEmpty()).thenReturn(false)
            whenever(localDataSource.getPopularArtists()).thenReturn(localArtists)

            val result = artistRepository.getPopularArtists()

            assertEquals(localArtists, result)
        }
    }

    @Test
    fun `getPopularMovies saves remote data to local`(){
        runBlocking {
            val remoteArtist = listOf(mockedArtist.copy(2))
            whenever(localDataSource.isEmpty()).thenReturn(true)
            whenever(remoteDataSource.getPopularArtists(any(), any())).thenReturn(remoteArtist)
            whenever(regionRepository.findLastRegion()).thenReturn("ES")

            artistRepository.getPopularArtists()

            verify(localDataSource).saveArtists(remoteArtist)
        }
    }

    @Test
    fun `findByMbid calls local data source`(){
        runBlocking {
            val localArtist = mockedArtist.copy(mbid = "3")
            whenever(localDataSource.findByMbid("3")).thenReturn(localArtist)

            val result = artistRepository.findByMbid("3")

            assertEquals(localArtist,result)
        }
    }

    @Test
    fun `update updates local data source`(){
        runBlocking {
            val localArtist = mockedArtist.copy(3)

            artistRepository.update(localArtist)
            verify(localDataSource).update(localArtist)
        }
    }

}