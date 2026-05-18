package com.example.photocatalogapp.data.repository

import com.example.photocatalogapp.data.mapper.toDomain
import com.example.photocatalogapp.data.remote.PhotoApi
import com.example.photocatalogapp.domain.model.Photo
import com.example.photocatalogapp.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val api: PhotoApi
) : PhotoRepository {

    override suspend fun getPhotos(): List<Photo> {
        return api.getPhotos().map { it.toDomain() }
    }
}