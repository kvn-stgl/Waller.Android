package de.kevin_stieglitz.waller.ui

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.squareup.picasso.Picasso
import de.kevin_stieglitz.waller.R
import de.kevin_stieglitz.waller.extension.PicassoScaleTransform
import de.kevin_stieglitz.waller.extension.actionBarHeight
import de.kevin_stieglitz.waller.extension.cachedPicassoBitmap
import kotlinx.android.synthetic.main.activity_wallpaper_detail.*
import kotlinx.android.synthetic.main.sheet_activity_wallpaper_detail_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class WallpaperDetailActivity : AppCompatActivity() {

    private val wallpaperArgs by navArgs<WallpaperDetailActivityArgs>()

    private val detailViewModel: WallpaperDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_wallpaper_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initImage()
        initBottomSheet()
    }

    private fun initBottomSheet() {
        fabLove.scaleX = 0f
        fabLove.scaleY = 0f

        val bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN;
        bottomSheetBehavior.peekHeight = bottomSheetBehavior.peekHeight + actionBarHeight()
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                sheetArrowUp.rotation = slideOffset * 180;
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (BottomSheetBehavior.STATE_DRAGGING == newState || BottomSheetBehavior.STATE_SETTLING == newState) {
                    fabLove.animate().scaleX(0F).scaleY(0F).setDuration(300).start();
                } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    fabLove.animate().scaleX(1F).scaleY(1F).setDuration(300).start();
                }
            }

        })

        wallpaperPhotoView.setOnClickListener {
            bottomSheetBehavior.state = when (bottomSheetBehavior.state) {
                BottomSheetBehavior.STATE_EXPANDED -> BottomSheetBehavior.STATE_COLLAPSED
                BottomSheetBehavior.STATE_COLLAPSED -> BottomSheetBehavior.STATE_HIDDEN
                BottomSheetBehavior.STATE_HIDDEN -> BottomSheetBehavior.STATE_COLLAPSED
                else -> BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }

    private fun initImage() {
        wallpaperPhotoView.transitionName = getString(R.string.transition_wallpaper, wallpaperArgs.imageId)
        wallpaperPhotoView.scaleType = ImageView.ScaleType.CENTER_CROP

        // Back Button
        back.setOnClickListener {
            onBackPressed()
        }

        wallpaperPhotoView.setImageBitmap(this.cachedPicassoBitmap(wallpaperArgs.imageThumb))

        val displayMetrics = Resources.getSystem().displayMetrics
        detailViewModel.wallpaper(wallpaperArgs.imageId).observe(this, Observer {
            Picasso.with(this)
                .load(it.path)
                .transform(PicassoScaleTransform(displayMetrics.heightPixels, displayMetrics.widthPixels))
                .noPlaceholder()
                .into(wallpaperPhotoView)
        })
    }
}
