package de.kevin_stieglitz.waller.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.kevin_stieglitz.waller.backend.Rest
import de.kevin_stieglitz.waller.extension.toLiveData
import de.kevin_stieglitz.waller.model.WallpaperSearchData
import io.reactivex.schedulers.Schedulers

class WallpaperListViewModel : ViewModel() {

    fun wallpapers(type: DisplayType?): LiveData<WallpaperSearchData> {
        return Rest.waller
            .search(sorting = type?.sorting)
            .subscribeOn(Schedulers.io())
            .toFlowable()
            .toLiveData()
    }

}
