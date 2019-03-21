package de.jambit.waller

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import de.jambit.waller.adapter.WallpaperAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var adapter = WallpaperAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Rest.retrofit
            .search()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    adapter.list = it.images
                },
                {
                    Log.e("TEST", "ERROR", it)
                })

        recyclerview_images.adapter = adapter
        recyclerview_images.layoutManager = GridLayoutManager(this, 2)
    }
}
