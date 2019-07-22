package de.kevin_stieglitz.waller.backend

import de.kevin_stieglitz.waller.model.WallpaperData
import de.kevin_stieglitz.waller.model.WallpaperSearchData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface WallerApi {

    @GET("/api/v1/search")
    fun search(
        @QueryMap searchOptions: Map<String, String>? = null,
        @Query("page") page: Int? = null
    ): Single<WallpaperSearchData>;

    @GET("/api/v1/w/{id}")
    fun detail(@Path("id") id: String): Single<WallpaperData>;
}