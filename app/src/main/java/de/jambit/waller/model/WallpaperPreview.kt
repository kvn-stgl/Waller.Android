package de.jambit.waller.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WallpaperPreview(
    @SerializedName("favorites") val favorites: Int,
    @SerializedName("id") val id: Long,
    @SerializedName("resolution") val resolution: String,
    @SerializedName("thumb") val thumb: String

) : Parcelable