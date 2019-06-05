package de.kevin_stieglitz.waller.database

import androidx.paging.DataSource
import androidx.room.*
import de.kevin_stieglitz.waller.model.WallpaperSearchEntry
import io.reactivex.Completable

@Dao
interface WallpaperSearchEntryDao {

    @Query("SELECT * FROM wallpapersearchentry")
    fun getDataSource(): DataSource.Factory<Int, WallpaperSearchEntry>

    @Delete
    fun delete(entity: WallpaperSearchEntry): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<WallpaperSearchEntry>): Completable
}