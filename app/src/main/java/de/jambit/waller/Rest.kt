package de.jambit.waller

import de.jambit.waller.model.WallerApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Rest {

    private const val BASE_URL = "https://waller-235210.appspot.com"

    val retrofit: WallerApi

    init {

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
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WallerApi::class.java)
    }
}