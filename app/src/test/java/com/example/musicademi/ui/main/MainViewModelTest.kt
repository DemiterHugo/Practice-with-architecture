package com.example.musicademi.ui.main



import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demiter.usescases.GetPopularArtists
import com.example.musicademi.ui.main.MainViewModel.UiModel
import androidx.lifecycle.Observer
import com.demiter.lib.mockedArtist
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
import java.util.*


@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()        //para poder testear livedata ya que siempre busca ejecutar los observers en el hilo principal
    @Mock
    lateinit var getPopularArtists: GetPopularArtists
    @Mock
    lateinit var observer: Observer<UiModel>        //tenemos que testear livedata pero no tenemos un lifecycle, asi q no podemos utilizar el observer de siempre,
                                                    // de modo que usaremos la funcion obserforever.

    private lateinit var vm: MainViewModel

    @Before
    fun setUp(){
        vm = MainViewModel(getPopularArtists,Dispatchers.Unconfined)
    }

    @Test
    fun `observing Livedata launches location permission request`(){
        vm.model.observeForever(observer)
        verify(observer).onChanged(UiModel.RequestLocationPermission)
    }

    @Test
    fun `after requesting the permission, loading is shown`(){
        runBlocking {
            val artist = listOf(mockedArtist.copy(1))
            whenever(getPopularArtists.invoke()).thenReturn(artist)
            vm.model.observeForever(observer)
            vm.onCoarsePermissionRequested()
            verify(observer).onChanged(UiModel.Loading)

        }
    }

    @Test
    fun `after requesting the permission, getPopularArtist is called`(){
        runBlocking {
            val artist = listOf(mockedArtist.copy(2))
            whenever(getPopularArtists.invoke()).thenReturn(artist)
            vm.model.observeForever(observer)
            vm.onCoarsePermissionRequested()
            verify(observer).onChanged(UiModel.Content(artist))
        }
    }


}