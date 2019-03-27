package de.jambit.waller.model

import com.google.gson.annotations.SerializedName

data class WallpaperPreview(
    @SerializedName("favorites") val favorites: Int,
    @SerializedName("id") val id: Long,
    @SerializedName("resolution") val resolution: String,
    @SerializedName("thumb") val thumb: String
)