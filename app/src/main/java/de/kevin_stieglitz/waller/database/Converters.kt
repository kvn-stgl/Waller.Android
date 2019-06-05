package de.kevin_stieglitz.waller.database

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.*


class Converters {
    private val gson = GsonBuilder().create()

    @TypeConverter
    fun stringToStringList(data: String?): List<String> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<String>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringListToString(someObjects: List<String>): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun longToDate(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToLong(value: Date?): Long? {
        return value?.time
    }
}