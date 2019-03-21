package de.jambit.waller.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WallerApi {
    @GET("/search")
    fun search(): Single<WallpaperPreviewList>;

    @GET("/image/{id}")
    fun image(@Path("id") id: Int): Single<Wallpaper>;
}