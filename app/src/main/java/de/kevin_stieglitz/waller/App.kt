package de.kevin_stieglitz.waller

import android.app.Application
import androidx.room.Room
import de.kevin_stieglitz.waller.backend.Rest
import de.kevin_stieglitz.waller.database.InMemoryDatabase
import de.kevin_stieglitz.waller.extension.setInMemoryDebugDatabase
import de.kevin_stieglitz.waller.repository.WallpaperSearchRepository
import de.kevin_stieglitz.waller.ui.WallpaperDetailViewModel
import de.kevin_stieglitz.waller.ui.WallpaperListViewModel
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.experimental.builder.single
import timber.log.Timber

class MainApp : Application() {

    val inMemoryDatabase by inject<InMemoryDatabase>()

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(appModule)
        }

        // add the inMemoryDatabase to the debug web interface
        if (BuildConfig.DEBUG) {
            inMemoryDatabase
                .openHelper
                .writableDatabase
                .setInMemoryDebugDatabase()
        }
    }

    private val appModule = module {

        single { Rest().waller }
        single {
            Room.inMemoryDatabaseBuilder(androidContext(), InMemoryDatabase::class.java)
                .fallbackToDestructiveMigration()
                .build()
        }

        single<WallpaperSearchRepository>()

        viewModel<WallpaperListViewModel>()
        viewModel<WallpaperDetailViewModel>()
    }
}