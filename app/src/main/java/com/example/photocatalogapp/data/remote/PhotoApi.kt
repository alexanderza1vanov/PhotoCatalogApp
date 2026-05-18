package com.example.photocatalogapp.data.remote

import retrofit2.http.GET

interface PhotoApi {

    @GET("isla-de-muerte/PhotoCatalogApp/master/photos.json")
    suspend fun getPhotos(): List<PhotoDto>
}