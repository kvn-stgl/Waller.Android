package de.kevin_stieglitz.waller.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


class WallpaperSearchData {

    @SerializedName("data")
    var wallpaperSearchEntries: List<WallpaperSearchEntry> = listOf()

    @SerializedName("meta")
    var meta: Meta = Meta()
}

@Entity
class WallpaperSearchEntry {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: String = ""

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("short_url")
    @Expose
    var shortUrl: String? = null

    @SerializedName("views")
    @Expose
    var views: Int? = null

    @SerializedName("favorites")
    @Expose
    var favorites: Int? = null

    @SerializedName("source")
    @Expose
    var source: String? = null

    @SerializedName("purity")
    @Expose
    var purity: String? = null

    @SerializedName("category")
    @Expose
    var category: String? = null

    @SerializedName("dimension_x")
    @Expose
    var dimensionX: Int? = null

    @SerializedName("dimension_y")
    @Expose
    var dimensionY: Int? = null

    @SerializedName("resolution")
    @Expose
    var resolution: String? = null

    @SerializedName("ratio")
    @Expose
    var ratio: String? = null

    @SerializedName("file_size")
    @Expose
    var fileSize: Int? = null

    @SerializedName("file_type")
    @Expose
    var fileType: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("colors")
    @Expose
    var colors: List<String>? = null

    @SerializedName("path")
    @Expose
    var path: String? = null

    @SerializedName("thumbs")
    @Expose
    @Embedded
    var thumbs: Thumbs? = null

    var requestedDate: Date = Date(System.currentTimeMillis())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WallpaperSearchEntry

        if (id != other.id) return false
        if (url != other.url) return false
        if (shortUrl != other.shortUrl) return false
        if (views != other.views) return false
        if (favorites != other.favorites) return false
        if (source != other.source) return false
        if (purity != other.purity) return false
        if (category != other.category) return false
        if (dimensionX != other.dimensionX) return false
        if (dimensionY != other.dimensionY) return false
        if (resolution != other.resolution) return false
        if (ratio != other.ratio) return false
        if (fileSize != other.fileSize) return false
        if (fileType != other.fileType) return false
        if (createdAt != other.createdAt) return false
        if (colors != other.colors) return false
        if (path != other.path) return false
        if (thumbs != other.thumbs) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (shortUrl?.hashCode() ?: 0)
        result = 31 * result + (views ?: 0)
        result = 31 * result + (favorites ?: 0)
        result = 31 * result + (source?.hashCode() ?: 0)
        result = 31 * result + (purity?.hashCode() ?: 0)
        result = 31 * result + (category?.hashCode() ?: 0)
        result = 31 * result + (dimensionX ?: 0)
        result = 31 * result + (dimensionY ?: 0)
        result = 31 * result + (resolution?.hashCode() ?: 0)
        result = 31 * result + (ratio?.hashCode() ?: 0)
        result = 31 * result + (fileSize ?: 0)
        result = 31 * result + (fileType?.hashCode() ?: 0)
        result = 31 * result + (createdAt?.hashCode() ?: 0)
        result = 31 * result + (colors?.hashCode() ?: 0)
        result = 31 * result + (path?.hashCode() ?: 0)
        result = 31 * result + (thumbs?.hashCode() ?: 0)
        return result
    }


}


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
    val total: Int? = null,

    @SerializedName("query")
    @Expose
    val query: QueryTag? = null
)

class QueryTag {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("tag")
    @Expose
    var tag: String? = null

}