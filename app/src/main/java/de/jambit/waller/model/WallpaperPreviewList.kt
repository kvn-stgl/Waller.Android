package de.jambit.waller.model

import com.google.gson.annotations.SerializedName

data class WallpaperPreview(
    @SerializedName("favorites") val favorites: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("resolution") val resolution: String,
    @SerializedName("thumb") val thumb: String
)

data class WallpaperPreviewList(
    @SerializedName("images") val images: List<WallpaperPreview>,
    @SerializedName("pages") val pages: Int
)
