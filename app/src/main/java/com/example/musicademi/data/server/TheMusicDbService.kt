package com.example.musicademi.data.server

import retrofit2.http.*

interface TheMusicDbService {

    /*@GET("discover/movie?sort_by=popularity.desc")
    suspend fun listPopularMoviesAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): MovieDbResult*/

    //@GET("?method=artist.gettopalbums&artist=cher&api_key={api_key}&format=json")

    @GET("?method=artist.gettopalbums&format=json")

    suspend fun listTopAlbumsAsync(@Query("artist") artista: String,
                                   @Query("api_key") apiKey: String): TheTopAlbumDbResult


    @GET("?method=geo.gettopartists&format=json")

   suspend fun listTopArtistsAsync(@Query("country") region: String,
                                   @Query("api_key") apiKey: String): TheTopArtistsDbResult
}