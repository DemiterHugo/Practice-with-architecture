package com.example.musicademi.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.demiter.lib.mockedAlbum
import com.demiter.lib.mockedArtist
import com.demiter.usescases.FindArtistByMbid
import com.demiter.usescases.GetPopularAlbums
import com.demiter.usescases.ToggleArtistFavorite
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get: Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    lateinit var findArtistByMbid: FindArtistByMbid
    @Mock
    lateinit var getPopularAlbums: GetPopularAlbums
    @Mock
    lateinit var observer: Observer<DetailViewModel.UiModel>
    @Mock
    lateinit var toggleArtistFavorite: ToggleArtistFavorite

    private lateinit var vm: DetailViewModel

    @Before
    fun setUp(){
        vm = DetailViewModel("5441c29d-3602-4898-b1a1-b77fa23b8e50",findArtistByMbid,getPopularAlbums,toggleArtistFavorite,
            Dispatchers.Unconfined)
    }

    @Test
    fun `observing Livedata finds the movie`(){
        runBlocking {
            val artist = mockedArtist.copy(1)
            whenever(findArtistByMbid.invoke(any())).thenReturn(artist)
            vm.model.observeForever(observer)

            verify(observer).onChanged(DetailViewModel.UiModel.TheArtist(artist))
        }
    }

    @Test
    fun `observing Livedata shows albums of the artist`(){
        runBlocking {
            val artist = mockedArtist.copy(2)
            val album = listOf(mockedAlbum.copy(1))
            whenever(findArtistByMbid.invoke(any())).thenReturn(artist)
            whenever(getPopularAlbums.invoke()).thenReturn(album)
            vm.model.observeForever(observer)

            verify(observer).onChanged(DetailViewModel.UiModel.Content(album))
        }
    }

    @Test
    fun  `when favorite clicked, the toggleArtistFavorite use case is invocked`(){
        runBlocking {
            val artist = mockedArtist.copy(3)
            val album = listOf(mockedAlbum.copy(2))
            whenever(findArtistByMbid.invoke(any())).thenReturn(artist)
            whenever(getPopularAlbums.invoke()).thenReturn(album)

            whenever(toggleArtistFavorite.invoke(artist)).thenReturn(artist.copy(favorite = !artist.favorite))
            vm.model.observeForever(observer)
            vm.onFavoriteClicked()

            verify(toggleArtistFavorite).invoke(artist)
        }
    }
}