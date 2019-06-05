package de.kevin_stieglitz.waller.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.kevin_stieglitz.waller.backend.WallerApi
import de.kevin_stieglitz.waller.extension.toLiveData
import de.kevin_stieglitz.waller.model.Wallpaper
import io.reactivex.schedulers.Schedulers

class WallpaperDetailViewModel(
    private val wallerApi: WallerApi
) : ViewModel() {

    fun wallpaper(id: String): LiveData<Wallpaper> {
        return wallerApi
            .detail(id)
            .subscribeOn(Schedulers.io())
            .map { it.data }
            .toFlowable()
            .toLiveData()
    }

}
