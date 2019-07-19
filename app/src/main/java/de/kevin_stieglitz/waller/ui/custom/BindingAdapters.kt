package de.kevin_stieglitz.waller.ui.custom

import android.annotation.SuppressLint
import android.text.format.Formatter
import android.widget.TextView
import androidx.databinding.BindingAdapter
import de.kevin_stieglitz.waller.model.Tag
import java.text.DateFormat
import java.util.*

@SuppressLint("SetTextI18n")
@BindingAdapter("upperChar")
fun TextView.setUpperChar(str: String?) {
    if (str != null) {
        text = str.substring(0, 1).toUpperCase() + str.substring(1)
    }
}

@BindingAdapter("fileSize")
fun TextView.setFileSize(sizeInBytes: Long?) {
    if (sizeInBytes != null) {
        text = Formatter.formatFileSize(context, sizeInBytes)
    }
}

@BindingAdapter("date")
fun TextView.setDate(date: Date?) {
    if (date != null) {
        text = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date)
    }
}

@BindingAdapter("wallpaperTags")
fun TextView.setWallpaperTags(tags: List<Tag>?) {
    if (tags != null) {
        text = tags.joinToString(separator = " ") { if (it.name == null) "" else "#" + it.name }
    }
}

@BindingAdapter("wallpaperTag")
fun TextView.setWallpaperTag(tags: List<Tag>?) {
    if (tags != null && tags.isNotEmpty()) {
        text = tags.get(0).name
    }
}