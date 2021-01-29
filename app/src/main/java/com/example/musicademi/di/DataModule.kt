package com.example.musicademi.di

import com.demiter.data.repository.AlbumsRepository
import com.demiter.data.repository.ArtistRepository
import com.demiter.data.repository.PermissionChecker
import com.demiter.data.repository.RegionRepository
import com.demiter.data.source.LocalDataSource
import com.demiter.data.source.LocationDataSource
import com.demiter.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DataModule(){



    @Provides
    fun artistRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        regionRepository: RegionRepository,
        @Named("apikey")apikey:String
    ) = ArtistRepository(localDataSource, remoteDataSource, regionRepository,apikey)

    @Provides
    fun regionRepositoryProvider(
        locationDataSource: LocationDataSource,
        permissionChecker: PermissionChecker
    ) = RegionRepository(locationDataSource, permissionChecker)

}