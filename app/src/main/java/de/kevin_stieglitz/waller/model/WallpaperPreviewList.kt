package de.kevin_stieglitz.waller.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class WallpaperSearchData {

    @SerializedName("data")
    val wallpaperSearchEntries: List<WallpaperSearchEntry> = listOf()

    @SerializedName("meta")
    val meta: Meta? = null
}


class WallpaperSearchEntry {

    @SerializedName("id")
    @Expose
    val id: String? = null

    @SerializedName("url")
    @Expose
    val url: String? = null

    @SerializedName("short_url")
    @Expose
    val shortUrl: String? = null

    @SerializedName("views")
    @Expose
    val views: Int? = null

    @SerializedName("favorites")
    @Expose
    val favorites: Int? = null

    @SerializedName("source")
    @Expose
    val source: String? = null

    @SerializedName("purity")
    @Expose
    val purity: String? = null

    @SerializedName("category")
    @Expose
    val category: String? = null

    @SerializedName("dimension_x")
    @Expose
    val dimensionX: Int? = null

    @SerializedName("dimension_y")
    @Expose
    val dimensionY: Int? = null

    @SerializedName("resolution")
    @Expose
    val resolution: String? = null

    @SerializedName("ratio")
    @Expose
    val ratio: String? = null

    @SerializedName("file_size")
    @Expose
    val fileSize: Int? = null

    @SerializedName("file_type")
    @Expose
    val fileType: String? = null

    @SerializedName("created_at")
    @Expose
    val createdAt: String? = null

    @SerializedName("colors")
    @Expose
    val colors: List<String>? = null

    @SerializedName("path")
    @Expose
    val path: String? = null

    @SerializedName("thumbs")
    @Expose
    val thumbs: Thumbs? = null

}


class Meta {

    @SerializedName("current_page")
    @Expose
    val currentPage: Int? = null

    @SerializedName("last_page")
    @Expose
    val lastPage: Int? = null

    @SerializedName("per_page")
    @Expose
    val perPage: Int? = null

    @SerializedName("total")
    @Expose
    val total: Int? = null

    @SerializedName("query")
    @Expose
    val query: QueryTag? = null
}

class QueryTag {

    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("tag")
    @Expose
    val tag: String? = null

}