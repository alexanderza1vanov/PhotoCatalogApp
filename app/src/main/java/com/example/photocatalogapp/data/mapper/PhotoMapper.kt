package com.example.photocatalogapp.data.mapper

import com.example.photocatalogapp.data.remote.PhotoDto
import com.example.photocatalogapp.domain.model.Photo

fun PhotoDto.toDomain(): Photo {
    return Photo(
        id = id,
        author = author,
        width = width,
        height = height,
        url = url,
        downloadUrl = downloadUrl
    )
}