package de.kevin_stieglitz.waller.ui

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.uber.autodispose.autoDisposable
import de.kevin_stieglitz.waller.R
import de.kevin_stieglitz.waller.backend.WallerApi
import de.kevin_stieglitz.waller.model.Wallpaper
import de.kevin_stieglitz.waller.ui.custom.AutoDisposeViewModel
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class WallpaperDetailViewModel(
    private val wallerApi: WallerApi,
    private val context: Context
) : AutoDisposeViewModel() {

    val wallpaper = MutableLiveData<Wallpaper>()

    val tagAdapter = Transformations.map(wallpaper) {
        ArrayAdapter<String>(context, R.layout.view_detail_grid_tag, it.tags.map { "#" + it.name })
    }

    val colorAdapter = Transformations.map(wallpaper) {
        ArrayAdapter<String>(context, R.layout.view_detail_grid_tag, it.colors.map { "#$it" })
    }

    fun loadWallpaper(id: String) {
        wallerApi
            .detail(id)
            .subscribeOn(Schedulers.io())
            .autoDisposable(this)
            .subscribe({ wallpaper.postValue(it.data) }, { Timber.e(it, "Error receiving loadWallpaper") })
    }

}
