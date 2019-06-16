package de.kevin_stieglitz.waller.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import de.kevin_stieglitz.waller.model.DisplayType
import de.kevin_stieglitz.waller.repository.WallpaperSearchRepository

class WallpaperListViewModel(
    private val wallpaperSearchRepository: WallpaperSearchRepository
) : ViewModel() {

    private val searchCategory = MutableLiveData<String>()
    private val repoResult = map(searchCategory) {
        wallpaperSearchRepository.wallpaper(sorting = it)
    }
    val posts = switchMap(repoResult) { it.pagedList }
    val networkState = switchMap(repoResult) { it.networkState }
    val refreshState = switchMap(repoResult) { it.refreshState }

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun search(type: DisplayType?): Boolean {
        if (searchCategory.value == type?.sorting) {
            return false
        }
        searchCategory.value = type?.sorting
        return true
    }

    fun retry() {
        val listing = repoResult.value
        listing?.retry?.invoke()
    }
}
