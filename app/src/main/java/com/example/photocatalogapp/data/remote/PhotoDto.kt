package com.example.photocatalogapp.data.remote

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    val id: Int,
    val name: String,
    @SerializedName("russian")
    val russianName: String?,
    val image: ShikimoriImageDto?
)

data class ShikimoriImageDto(
    val original: String?,
    val preview: String?,
    val x96: String?,
    val x48: String?
)