package de.kevin_stieglitz.waller.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.kevin_stieglitz.waller.backend.Rest
import de.kevin_stieglitz.waller.extension.toLiveData
import de.kevin_stieglitz.waller.model.Wallpaper
import io.reactivex.schedulers.Schedulers

class WallpaperDetailViewModel : ViewModel() {

    fun wallpaper(id: String): LiveData<Wallpaper> {
        return Rest.waller
            .detail(id)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .toFlowable()
            .toLiveData()
    }

}
