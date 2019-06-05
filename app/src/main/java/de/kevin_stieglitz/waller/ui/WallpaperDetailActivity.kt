package de.kevin_stieglitz.waller.ui

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso
import de.kevin_stieglitz.waller.R
import de.kevin_stieglitz.waller.extension.cachedPicassoBitmap
import de.kevin_stieglitz.waller.ui.custom.ImageSwitcherPicasso
import kotlinx.android.synthetic.main.activity_wallpaper_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class WallpaperDetailActivity : AppCompatActivity() {

    private val wallpaperArgs by navArgs<WallpaperDetailActivityArgs>()

    private val detailViewModel: WallpaperDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_wallpaper_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {

        imageswitcher_wallpaper.transitionName = getString(R.string.transition_wallpaper, wallpaperArgs.imageId)

        // Back Button
        back.setOnClickListener {
            onBackPressed()
        }

        val thumbUri = Uri.parse(wallpaperArgs.imageThumb)

        // Animation when switching to another image.
        val animOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out)
        val animIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)

        imageswitcher_wallpaper.inAnimation = animIn
        imageswitcher_wallpaper.outAnimation = animOut
        imageswitcher_wallpaper.setFactory {
            val imageView = PhotoView(this)
            imageView.layoutParams =
                FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            return@setFactory imageView
        }


        imageswitcher_wallpaper.setImageDrawable(
            BitmapDrawable(
                this.resources,
                this.cachedPicassoBitmap(wallpaperArgs.imageThumb)
            )
        )

        val imageSwitcher = ImageSwitcherPicasso(this, imageswitcher_wallpaper)

        detailViewModel.wallpaper(wallpaperArgs.imageId).observe(this, Observer {
            Picasso.with(this)
                .load(it.path)
                .noPlaceholder()
                .into(imageSwitcher)
        })
    }
}
