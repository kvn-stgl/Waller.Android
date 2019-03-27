package de.jambit.waller.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.jambit.waller.backend.Rest
import de.jambit.waller.extension.toLiveData
import de.jambit.waller.model.WallpaperPreviewList
import io.reactivex.schedulers.Schedulers

class WallpaperListViewModel : ViewModel() {

    fun wallpapers(type: DisplayType?): LiveData<WallpaperPreviewList> {
        return Rest.retrofit
            .search(sorting = type?.sorting)
            .subscribeOn(Schedulers.io())
            .toFlowable()
            .toLiveData()
    }

}
