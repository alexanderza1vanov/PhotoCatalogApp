package com.example.photocatalogapp.data.remote

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @SerializedName("download_url")
    val downloadUrl: String
)