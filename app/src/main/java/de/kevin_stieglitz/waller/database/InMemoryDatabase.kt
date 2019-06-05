package de.kevin_stieglitz.waller.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.kevin_stieglitz.waller.model.WallpaperSearchEntry

@TypeConverters(Converters::class)
@Database(
    entities = [
        WallpaperSearchEntry::class
    ], version = 1
)
abstract class InMemoryDatabase : RoomDatabase() {

    abstract fun wallpaperSearchEntryDao(): WallpaperSearchEntryDao

}