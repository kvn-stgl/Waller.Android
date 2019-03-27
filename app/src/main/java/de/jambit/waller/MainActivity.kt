package de.jambit.waller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import de.jambit.waller.adapter.WallpaperAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private val scopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    var adapter = WallpaperAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Rest.retrofit
            .search()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDisposable(scopeProvider)
            .subscribe({ adapter.list = it.images }, { Timber.e(it) })

        recyclerview_images.adapter = adapter
        recyclerview_images.layoutManager = GridLayoutManager(this, 2)
    }
}
