package de.jambit.waller.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.jambit.waller.backend.Rest
import de.jambit.waller.extension.toLiveData
import de.jambit.waller.model.Wallpaper
import io.reactivex.schedulers.Schedulers

class WallpaperDetailViewModel : ViewModel() {

    fun wallpaper(id: Long): LiveData<Wallpaper> {
        return Rest.retrofit
            .image(id)
            .subscribeOn(Schedulers.io())
            .toFlowable()
            .toLiveData()
    }

}
