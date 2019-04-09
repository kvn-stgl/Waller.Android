package de.kevin_stieglitz.waller.backend

import de.kevin_stieglitz.waller.model.Wallpaper
import de.kevin_stieglitz.waller.model.WallpaperPreviewList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WallerApi {

    @GET("/search")
    fun search(@Query("sorting") sorting: String?): Single<WallpaperPreviewList>;

    @GET("/image/details/{id}")
    fun detail(@Path("id") id: Long): Single<Wallpaper>;
}