package de.kevin_stieglitz.waller.backend

import com.google.gson.GsonBuilder
import de.kevin_stieglitz.waller.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class Rest {

    private val BASE_URL = "https://wallhaven.cc"

    val waller: WallerApi

    init {

        val logging = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            logging.level = HttpLoggingInterceptor.Level.HEADERS

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

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

        waller = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WallerApi::class.java)
    }
}