package com.example.photocatalogapp.presentation.list

import com.example.photocatalogapp.domain.model.Photo

sealed interface PhotoListUiState {
    data object Loading : PhotoListUiState
    data class Success(val photos: List<Photo>) : PhotoListUiState
    data class Error(val message: String) : PhotoListUiState
}