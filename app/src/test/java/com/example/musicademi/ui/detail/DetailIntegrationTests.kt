package com.example.musicademi.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.musicademi.ui.detail.DetailViewModel.UiModel
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import androidx.lifecycle.Observer
import com.demiter.data.source.LocalDataSource
import com.demiter.lib.mockedAlbum
import com.demiter.lib.mockedArtist
import com.demiter.usescases.FindArtistByMbid
import com.demiter.usescases.GetPopularAlbums
import com.demiter.usescases.ToggleArtistFavorite
import com.example.musicademi.ui.FakeLocalDataSource
import com.example.musicademi.ui.defaultFakeAlbums
import com.example.musicademi.ui.defaultFakeArtists
import com.example.musicademi.ui.initMockedDi
import com.example.musicademi.ui.main.MainViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertTrue

import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.test.get

@RunWith(MockitoJUnitRunner::class)
class DetailIntegrationTests: AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<UiModel>

    private lateinit var vm: DetailViewModel
    private lateinit var localDataSource: FakeLocalDataSource

    @Before
    fun setUp(){
        val vmModule = module {
            factory { (mbidArtist:String)->DetailViewModel(mbidArtist,get(),get(),get(),get()) }
            factory { FindArtistByMbid(get()) }
            factory { GetPopularAlbums(get()) }
            factory { ToggleArtistFavorite(get()) }
        }

        initMockedDi(vmModule)
        vm = get { parametersOf(1) }

        localDataSource = get<LocalDataSource>() as FakeLocalDataSource
        localDataSource.artists = defaultFakeArtists
        localDataSource.albums = defaultFakeAlbums
    }

    @Test
    fun `observing LiveData finds the artist`(){
        vm.model.observeForever(observer)
        verify(observer).onChanged(DetailViewModel.UiModel.TheArtist(mockedArtist.copy(1)))
    }

    @Test
    fun `observing LiveData finds the albums`(){
        vm.model.observeForever(observer)
        verify(observer).onChanged(DetailViewModel.UiModel.Content(defaultFakeAlbums))
    }

    @Test
    fun `favorite is updated in local data source`(){
        vm.model.observeForever(observer)
        vm.onFavoriteClicked()
        runBlocking {
            assertTrue(localDataSource.findByMbid(any()).favorite)
        }
    }
}