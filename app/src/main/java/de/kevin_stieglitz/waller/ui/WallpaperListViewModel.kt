package de.kevin_stieglitz.waller.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import de.kevin_stieglitz.waller.model.WallpaperSearchOptions
import de.kevin_stieglitz.waller.repository.WallpaperSearchRepository

class WallpaperListViewModel(
    private val wallpaperSearchRepository: WallpaperSearchRepository
) : ViewModel() {

    private val searchOptions = MutableLiveData<WallpaperSearchOptions>()
    private val repoResult = map(searchOptions) {
        wallpaperSearchRepository.wallpaper(searchOptions = it)
    }
    val posts = switchMap(repoResult) { it.pagedList }
    val networkState = switchMap(repoResult) { it.networkState }
    val refreshState = switchMap(repoResult) { it.refreshState }

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun search(wallpaperSearchOptions: WallpaperSearchOptions): Boolean {
        if (searchOptions.value == wallpaperSearchOptions) {
            return false
        }
        searchOptions.postValue(wallpaperSearchOptions.copy())
        return true
    }

    fun retry() {
        val listing = repoResult.value
        listing?.retry?.invoke()
    }
}