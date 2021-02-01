package com.demiter.data.repository

import com.demiter.data.repository.PermissionChecker.Permission.COARSE_LOCATION
import com.demiter.data.source.LocationDataSource
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegionRepositoryTest {
    @Mock
    lateinit var locationDataSource: LocationDataSource
    @Mock
    lateinit var permissionChecker: PermissionChecker

    private lateinit var regionRepository: RegionRepository

    @Before
    fun setUp(){
        regionRepository = RegionRepository(locationDataSource,permissionChecker)
    }

    @Test
    fun `returns default when coarse permission not granted`(){
        runBlocking {
            whenever(permissionChecker.check(COARSE_LOCATION)).thenReturn(false)
            val region = regionRepository.findLastRegion()

            assertEquals(RegionRepository.DEFAULT_REGION,region)
        }
    }

    @Test
    fun `returns region from location data source when permission granted`(){
        runBlocking {
            whenever(permissionChecker.check(COARSE_LOCATION)).thenReturn(true)
            val region = regionRepository.findLastRegion()

            assertEquals("spain",region)
        }
    }

}