package com.example.photocatalogapp.domain.repository

import com.example.photocatalogapp.domain.model.Photo

interface PhotoRepository {
    suspend fun getPhotos(): List<Photo>
}