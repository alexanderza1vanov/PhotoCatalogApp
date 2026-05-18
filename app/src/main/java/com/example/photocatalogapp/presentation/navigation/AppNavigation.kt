package com.example.photocatalogapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.photocatalogapp.domain.model.Photo
import com.example.photocatalogapp.presentation.detail.PhotoDetailScreen
import com.example.photocatalogapp.presentation.list.PhotoListScreen

@Composable
fun AppNavigation() {
    val selectedPhoto = remember {
        mutableStateOf<Photo?>(null)
    }

    if (selectedPhoto.value == null) {
        PhotoListScreen(
            onPhotoClick = { photo ->
                selectedPhoto.value = photo
            }
        )
    } else {
        PhotoDetailScreen(
            photo = selectedPhoto.value!!,
            onBackClick = {
                selectedPhoto.value = null
            }
        )
    }
}