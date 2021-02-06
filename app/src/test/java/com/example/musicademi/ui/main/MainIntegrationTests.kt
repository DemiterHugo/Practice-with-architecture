package com.example.musicademi.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.demiter.data.source.LocalDataSource
import com.demiter.lib.mockedArtist
import com.demiter.usescases.GetPopularArtists
import com.example.musicademi.ui.FakeLocalDataSource
import com.example.musicademi.ui.defaultFakeArtists
import com.example.musicademi.ui.initMockedDi
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainIntegrationTests: AutoCloseKoinTest() {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    lateinit var observer: Observer<MainViewModel.UiModel>

    private lateinit var vm: MainViewModel

    @Before
    fun setUp(){
        val vmModule = module {
            factory { MainViewModel(get(),get()) }
            factory { GetPopularArtists(get()) }
        }

        initMockedDi(vmModule)
        vm = get()
    }

    @Test
    fun `data is loaded from server when local source is empty`(){
        vm.model.observeForever(observer)
        vm.onCoarsePermissionRequested()
        verify(observer).onChanged(MainViewModel.UiModel.Content(defaultFakeArtists))
    }

    @Test
    fun `data is loaded from local source when available`(){
        val fakeLocalArtists = listOf(mockedArtist.copy(10), mockedArtist.copy(11))
        val localDataSource = get<LocalDataSource>() as FakeLocalDataSource
        localDataSource.artists = fakeLocalArtists
        vm.model.observeForever(observer)
        vm.onCoarsePermissionRequested()
        verify(observer).onChanged(MainViewModel.UiModel.Content(fakeLocalArtists))
    }
}