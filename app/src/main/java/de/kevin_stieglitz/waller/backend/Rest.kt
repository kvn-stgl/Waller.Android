package de.kevin_stieglitz.waller.backend

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Rest {

    private const val BASE_URL = "https://wallhaven.cc"

    private const val WALLER_RESIZE_URL = "image/resize/%d/%d.jpg"

    val waller: WallerApi

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        val cacheSize = 10 * 1024 * 1024 // 10MB

        val client = OkHttpClient().newBuilder()
//            .cache(Cache(context.getCacheDir(), cacheSize)
            .addInterceptor({
                val builder = it.request().newBuilder()
//                if (!context.checkConnextion()) {
//                    builder.cacheControl(CacheControl.FORCE_CACHE)
//                }

                return@addInterceptor it.proceed(builder.build())
            })
            .addInterceptor(logging)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        waller = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WallerApi::class.java)
    }

    fun wallerResizeUrl(id: Long, height: Int): String {
        return BASE_URL + WALLER_RESIZE_URL.format(id, height)
    }
}