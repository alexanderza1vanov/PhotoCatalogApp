package com.example.photocatalogapp.data.mapper

import com.example.photocatalogapp.data.remote.PhotoDto
import com.example.photocatalogapp.domain.model.Photo

private const val SHIKIMORI_BASE_URL = "https://shikimori.one"

fun PhotoDto.toDomain(): Photo {
    val title = russianName
        ?.takeIf { it.isNotBlank() }
        ?: name

    val imagePath = image?.original
        ?: image?.preview
        ?: image?.x96
        ?: image?.x48
        ?: ""

    val imageUrl = if (imagePath.startsWith("http")) {
        imagePath
    } else {
        SHIKIMORI_BASE_URL + imagePath
    }

    return Photo(
        id = id.toString(),
        author = title,
        width = 300,
        height = 450,
        url = "https://shikimori.one/animes/$id",
        downloadUrl = imageUrl
    )
}