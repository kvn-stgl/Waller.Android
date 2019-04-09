package de.kevin_stieglitz.waller.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Wallpaper {

    @SerializedName("Category")
    @Expose
    var category: String? = null

    @SerializedName("Favorites")
    @Expose
    var favorites: Int? = null

    @SerializedName("ImageColors")
    @Expose
    var imageColors: List<String>? = null

    @SerializedName("ImageUrl")
    @Expose
    var imageUrl: String? = null

    @SerializedName("Purity")
    @Expose
    var purity: String? = null

    @SerializedName("Ratio")
    @Expose
    var ratio: String? = null

    @SerializedName("Resolution")
    @Expose
    var resolution: String? = null

    @SerializedName("ShortUrl")
    @Expose
    var shortUrl: String? = null

    @SerializedName("Size")
    @Expose
    var size: String? = null

    @SerializedName("Tags")
    @Expose
    var tags: List<String>? = null

    @SerializedName("TagsEx")
    @Expose
    var tagsEx: List<TagsEx>? = null

    @SerializedName("UploadTime")
    @Expose
    var uploadTime: String? = null

    @SerializedName("Uploader")
    @Expose
    var uploader: Uploader? = null

    @SerializedName("Views")
    @Expose
    var views: Int? = null

}

class Avatar {

    @SerializedName("200")
    @Expose
    var image_200: String? = null

    @SerializedName("32")
    @Expose
    var image_32: String? = null

}

class TagsEx {

    @SerializedName("Id")
    var id: Int? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Type")
    var type: String? = null

}

class Uploader {

    @SerializedName("Avatar")
    @Expose
    var avatar: Avatar? = null

    @SerializedName("Username")
    @Expose
    var username: String? = null

}