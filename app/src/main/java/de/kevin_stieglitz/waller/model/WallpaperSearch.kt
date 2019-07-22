package de.kevin_stieglitz.waller.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


data class WallpaperSearchData(

    @SerializedName("data")
    var wallpaperSearchEntries: List<WallpaperSearchEntry> = listOf(),

    @SerializedName("meta")
    var meta: Meta = Meta()
)

@Entity
data class WallpaperSearchEntry(

    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: String = "",

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("short_url")
    @Expose
    var shortUrl: String? = null,

    @SerializedName("views")
    @Expose
    var views: Int? = null,

    @SerializedName("favorites")
    @Expose
    var favorites: Int? = null,

    @SerializedName("source")
    @Expose
    var source: String? = null,

    @SerializedName("purity")
    @Expose
    var purity: String? = null,

    @SerializedName("category")
    @Expose
    var category: String? = null,

    @SerializedName("dimension_x")
    @Expose
    var dimensionX: Int? = null,

    @SerializedName("dimension_y")
    @Expose
    var dimensionY: Int? = null,

    @SerializedName("resolution")
    @Expose
    var resolution: String? = null,

    @SerializedName("ratio")
    @Expose
    var ratio: String? = null,

    @SerializedName("file_size")
    @Expose
    var fileSize: Int? = null,

    @SerializedName("file_type")
    @Expose
    var fileType: String? = null,

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,

    @SerializedName("colors")
    @Expose
    var colors: List<String>? = null,

    @SerializedName("path")
    @Expose
    var path: String? = null,

    @SerializedName("thumbs")
    @Expose
    @Embedded
    var thumbs: Thumbs? = null,

    var requestedDate: Date = Date(System.currentTimeMillis())
)


data class Meta(

    @SerializedName("current_page")
    @Expose
    val currentPage: Int? = null,

    @SerializedName("last_page")
    @Expose
    val lastPage: Int? = null,

    @SerializedName("per_page")
    @Expose
    val perPage: Int? = null,

    @SerializedName("total")
    @Expose
    val total: Int? = null
)