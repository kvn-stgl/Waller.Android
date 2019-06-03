package de.kevin_stieglitz.waller.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class WallpaperData {

    @SerializedName("data")
    val data: Wallpaper = Wallpaper()
}


class Wallpaper {

    @SerializedName("id")
    val id: String? = null

    @SerializedName("url")
    @Expose
    val url: String? = null

    @SerializedName("short_url")
    @Expose
    val shortUrl: String? = null

    @SerializedName("uploader")
    @Expose
    val uploader: Uploader = Uploader()

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
    val colors: List<String> = listOf()

    @SerializedName("path")
    @Expose
    val path: String? = null

    @SerializedName("thumbs")
    @Expose
    val thumbs: Thumbs = Thumbs()

    @SerializedName("tags")
    @Expose
    val tags: List<Tag> = listOf()

}

class Thumbs {

    @SerializedName("large")
    val large: String? = null

    @SerializedName("original")
    val original: String? = null

    @SerializedName("small")
    val small: String? = null

}

class Uploader {

    @SerializedName("username")
    val username: String? = null

    @SerializedName("group")
    val group: String? = null

    @SerializedName("avatar")
    val avatar: Avatar = Avatar()

}


class Avatar {

    @SerializedName("200px")
    val image_200px: String? = null

    @SerializedName("128px")
    val image_128px: String? = null

    @SerializedName("32px")
    val image_32px: String? = null

    @SerializedName("20px")
    val image_20px: String? = null
}

class Tag {

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("alida")
    val type: String? = null

    @SerializedName("category_id")
    val categoryId: Int? = null

    @SerializedName("category")
    val category: String? = null

    @SerializedName("purity")
    val purity: String? = null

    @SerializedName("created_at")
    val createdAt: String? = null
}
