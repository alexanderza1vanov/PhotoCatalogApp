package com.example.photocatalogapp.domain.usecase

import com.example.photocatalogapp.domain.model.Photo
import com.example.photocatalogapp.domain.repository.PhotoRepository

class GetPhotosUseCase(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(): List<Photo> {
        return repository.getPhotos()
    }
}