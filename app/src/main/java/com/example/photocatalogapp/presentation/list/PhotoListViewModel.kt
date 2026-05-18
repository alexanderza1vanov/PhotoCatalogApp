package com.example.photocatalogapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photocatalogapp.data.remote.RetrofitInstance
import com.example.photocatalogapp.data.repository.PhotoRepositoryImpl
import com.example.photocatalogapp.domain.usecase.GetPhotosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhotoListViewModel : ViewModel() {

    private val getPhotosUseCase = GetPhotosUseCase(
        PhotoRepositoryImpl(RetrofitInstance.api)
    )

    private val _uiState = MutableStateFlow<PhotoListUiState>(PhotoListUiState.Loading)
    val uiState: StateFlow<PhotoListUiState> = _uiState.asStateFlow()

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch {
            _uiState.value = PhotoListUiState.Loading

            try {
                val photos = getPhotosUseCase()
                _uiState.value = PhotoListUiState.Success(photos)
            } catch (e: Exception) {
                _uiState.value = PhotoListUiState.Error(
                    message = e.message ?: "Не удалось загрузить фотографии"
                )
            }
        }
    }
}