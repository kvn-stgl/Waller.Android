package de.kevin_stieglitz.waller.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.facebook.common.executors.UiThreadImmediateExecutorService
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.DataSource
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.drawee.view.DraweeTransition
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import de.kevin_stieglitz.waller.R
import kotlinx.android.synthetic.main.wallpaper_detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class WallpaperDetailActivity : AppCompatActivity() {

    private val wallpaperArgs by navArgs<WallpaperDetailActivityArgs>()

    private val detailViewModel: WallpaperDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_wallpaper_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val fromScaleType = ScalingUtils.ScaleType.CENTER_CROP
            val toScaleType = ScalingUtils.ScaleType.CENTER_CROP

            window.sharedElementEnterTransition = DraweeTransition.createTransitionSet(
                fromScaleType, toScaleType, null, null
            )

            window.sharedElementReturnTransition = DraweeTransition.createTransitionSet(
                toScaleType, fromScaleType, null, null
            )
        }
    }

    private fun initView() {

        poster_image.transitionName = getString(R.string.transition_wallpaper, wallpaperArgs.imageId)

        val imagePipeline = Fresco.getImagePipeline()
        val thumbUri = Uri.parse(wallpaperArgs.imageThumb)

        // TODO: wrap into rxjava
        val thumbDataSource = imagePipeline.fetchDecodedImage(ImageRequest.fromUri(thumbUri), this)
        thumbDataSource.subscribe(object : BaseBitmapDataSubscriber() {
            override fun onNewResultImpl(bitmap: Bitmap?) {
                if (bitmap == null) return
                poster_image.hierarchy.setPlaceholderImage(BitmapDrawable(resources, bitmap))
            }

            override fun onFailureImpl(dataSource: DataSource<CloseableReference<CloseableImage>>?) {
                Timber.e("Error loading thumb data")
            }
        }, UiThreadImmediateExecutorService.getInstance())

        // Back Button
        back.setOnClickListener {
            onBackPressed()
        }

        detailViewModel.wallpaper(wallpaperArgs.imageId).observe(this, Observer {
            val posterUri = Uri.parse(it.path)
            val request = ImageRequestBuilder.newBuilderWithSource(posterUri)
                .setProgressiveRenderingEnabled(true)
                .build()
            val controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(poster_image.controller)
                .build()
            poster_image.controller = controller
        })
    }
}
