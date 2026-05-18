package com.example.photocatalogapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET("api/animes")
    suspend fun getPhotos(
        @Query("limit") limit: Int = 30,
        @Query("order") order: String = "popularity"
    ): List<PhotoDto>
}