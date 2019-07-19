package de.kevin_stieglitz.waller.model

import androidx.room.Dao
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


class WallpaperData {

    @SerializedName("data")
    var data: Wallpaper = Wallpaper()
}

@Dao
class Wallpaper {

    @SerializedName("id")
    var id: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("short_url")
    @Expose
    var shortUrl: String? = null

    @SerializedName("uploader")
    @Expose
    var uploader: Uploader = Uploader()

    @SerializedName("views")
    @Expose
    var views: Long? = null

    @SerializedName("favorites")
    @Expose
    var favorites: Long? = null

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
    var fileSize: Long? = null

    @SerializedName("file_type")
    @Expose
    var fileType: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: Date? = null

    @SerializedName("colors")
    @Expose
    var colors: List<String> = listOf()

    @SerializedName("path")
    @Expose
    var path: String? = null

    @SerializedName("thumbs")
    @Expose
    var thumbs: Thumbs = Thumbs()

    @SerializedName("tags")
    @Expose
    var tags: List<Tag> = listOf()

}

class Thumbs {

    @SerializedName("large")
    var large: String? = null

    @SerializedName("original")
    var original: String? = null

    @SerializedName("small")
    var small: String? = null

}

class Uploader {

    @SerializedName("username")
    var username: String? = null

    @SerializedName("group")
    var group: String? = null

    @SerializedName("avatar")
    var avatar: Avatar = Avatar()

}


class Avatar {

    @SerializedName("200px")
    var image_200px: String? = null

    @SerializedName("128px")
    var image_128px: String? = null

    @SerializedName("32px")
    var image_32px: String? = null

    @SerializedName("20px")
    var image_20px: String? = null
}

class Tag {

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("alida")
    var type: String? = null

    @SerializedName("category_id")
    var categoryId: Int? = null

    @SerializedName("category")
    var category: String? = null

    @SerializedName("purity")
    var purity: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    override fun toString(): String {
        return name ?: "null"
    }
}
