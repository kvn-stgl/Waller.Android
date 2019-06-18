package de.kevin_stieglitz.waller.ui

import androidx.lifecycle.MutableLiveData
import com.uber.autodispose.autoDisposable
import de.kevin_stieglitz.waller.backend.WallerApi
import de.kevin_stieglitz.waller.model.Wallpaper
import de.kevin_stieglitz.waller.ui.custom.AutoDisposeViewModel
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class WallpaperDetailViewModel(
    private val wallerApi: WallerApi
) : AutoDisposeViewModel() {

    val wallpaper = MutableLiveData<Wallpaper>()

    fun loadWallpaper(id: String) {
        wallerApi
            .detail(id)
            .subscribeOn(Schedulers.io())
            .autoDisposable(this)
            .subscribe({ wallpaper.postValue(it.data) }, { Timber.e(it, "Error receiving loadWallpaper") })
    }

}
