package de.kevin_stieglitz.waller.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import de.kevin_stieglitz.waller.backend.WallerApi
import de.kevin_stieglitz.waller.extension.NetworkState
import de.kevin_stieglitz.waller.model.WallpaperSearchEntry
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * A data source that uses the before/after keys returned in page requests.
 * <p>
 * See ItemKeyedSubredditDataSource
 */
class WallpaperPageKeyedDataSource(
    private val wallerApi: WallerApi,
    private val compositeDisposable: CompositeDisposable,
    private val sorting: String?
) : PageKeyedDataSource<Int, WallpaperSearchEntry>() {

    // keep a function reference for the retry event
    private var retry: (() -> Any)? = null

    /**
     * There is no sync on the state because paging will always call loadInitial first then wait
     * for it to return some success value before calling loadAfter.
     */
    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.invoke()
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, WallpaperSearchEntry>
    ) {
        // ignored, since we only ever append to our initial load
        Timber.v("params = [${params}], callback = [${callback}]")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, WallpaperSearchEntry>) {
        Timber.v("params = [${params}], callback = [${callback}]")

        networkState.postValue(NetworkState.LOADING)

        if (params.key < 1) return

        Timber.d("Try to load page ${params.key}")

        compositeDisposable.add(
            wallerApi
                .search(
                    page = params.key,
                    sorting = sorting
                )
                .subscribeOn(Schedulers.io())
                .subscribe({
                    retry = null
                    callback.onResult(it.wallpaperSearchEntries, it.meta!!.currentPage!! + 1)
                    networkState.postValue(NetworkState.LOADED)
                }, {
                    retry = {
                        loadAfter(params, callback)
                    }
                    networkState.postValue(NetworkState.error(it.message ?: "unknown err"))
                })
        )
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, WallpaperSearchEntry>
    ) {
        Timber.v("params = [${params}], callback = [${callback}]")

        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            wallerApi
                .search(page = 1, sorting = sorting)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    retry = null
                    callback.onResult(
                        it.wallpaperSearchEntries,
                        0,
                        it.meta.total!!,
                        null,
                        it.meta.currentPage!! + 1
                    )
                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)
                }, {
                    retry = {
                        loadInitial(params, callback)
                    }
                    networkState.postValue(NetworkState.error(it.message ?: "unknown err"))
                    initialLoad.postValue(NetworkState.LOADED)
                })
        )
    }
}