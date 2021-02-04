package com.demiter.data.repository

import com.demiter.data.source.LocalDataSource
import com.demiter.data.source.RemoteDataSource
import com.demiter.domain.Album
import com.demiter.lib.mockedAlbum
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
class AlbumsRepositoryTest {
    @Mock
    lateinit var localDataSource: LocalDataSource
    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var albumsRepository: AlbumsRepository
    private val apikey = "sdddsdsds"
    private val nameArtist = "Carlos"



    @Before
    fun setUp(){
        albumsRepository = AlbumsRepository(localDataSource,remoteDataSource,nameArtist,apikey)
    }

    @Test
    fun `getPopularAlbums gets from local data source first`(){
        runBlocking {
            val localAlbum = listOf(mockedAlbum.copy(1))

            whenever(localDataSource.isEmptybyName(any())).thenReturn(false)
            whenever(localDataSource.getPopularAlbumsByName(nameArtist)).thenReturn(localAlbum)

            val result = albumsRepository.getPopularAlbums()

            assertEquals(localAlbum,result)
        }
    }

    @Test
    fun `getPopularAlbums saves remote data to local`(){
        runBlocking {
            val remoteAlbum = listOf(mockedAlbum.copy(2))
            whenever(localDataSource.isEmptybyName(any())).thenReturn(true)
            whenever(remoteDataSource.getPopularAlbums(any(), any())).thenReturn(remoteAlbum)

            albumsRepository.getPopularAlbums()
            verify(localDataSource).saveAlbums(remoteAlbum)
        }
    }
}