package de.jambit.waller.backend

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object Rest {

    private const val BASE_URL = "https://waller-235210.appspot.com"

    val retrofit: WallerApi

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
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WallerApi::class.java)
    }
}