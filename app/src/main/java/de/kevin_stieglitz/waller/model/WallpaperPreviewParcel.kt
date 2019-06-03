package de.kevin_stieglitz.waller.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WallpaperPreviewParcel(
    val favorites: Int,
    val id: String,
    val resolution: String,
    val thumb: String

) : Parcelable