package de.kevin_stieglitz.waller.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.toLiveData
import de.kevin_stieglitz.waller.Constants.wallheavenElementsPerPage
import de.kevin_stieglitz.waller.backend.WallerApi
import de.kevin_stieglitz.waller.model.WallpaperSearchEntry
import de.kevin_stieglitz.waller.ui.WallpaperListViewState
import io.reactivex.disposables.CompositeDisposable

/**
 * Repository implementation that uses a database PagedList + a boundary callback to return a
 * listing that loads in pages.
 */
class WallpaperSearchRepository(
    private val wallerApi: WallerApi
) {
    fun wallpaper(sorting: String?): WallpaperListViewState<WallpaperSearchEntry> {
        val compositeDisposable = CompositeDisposable()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPageSize(wallheavenElementsPerPage)
            .build()

        val sourceFactory = object : DataSource.Factory<Int, WallpaperSearchEntry>() {
            val sourceLiveData = MutableLiveData<WallpaperPageKeyedDataSource>()
            override fun create(): DataSource<Int, WallpaperSearchEntry> {
                val source = WallpaperPageKeyedDataSource(wallerApi, compositeDisposable, sorting)
                sourceLiveData.postValue(source)
                return source
            }
        }

        return WallpaperListViewState(
            pagedList = sourceFactory
                .toLiveData(config),
            networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.networkState
            },
            retry = {
                sourceFactory.sourceLiveData.value?.retryAllFailed()
            },
            refresh = {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                it.initialLoad
            }
        )

    }
}
