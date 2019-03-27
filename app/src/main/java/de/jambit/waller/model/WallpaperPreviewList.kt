package de.jambit.waller.model

import com.google.gson.annotations.SerializedName

data class WallpaperPreviewList(
    @SerializedName("images") val images: List<WallpaperPreview>,
    @SerializedName("pages") val pages: Int
)
