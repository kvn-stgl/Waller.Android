package de.jambit.waller.backend

import de.jambit.waller.model.Wallpaper
import de.jambit.waller.model.WallpaperPreviewList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WallerApi {

    @GET("/search")
    fun search(@Query("sorting") sorting: String?): Single<WallpaperPreviewList>;

    @GET("/image/{id}")
    fun image(@Path("id") id: Long): Single<Wallpaper>;
}