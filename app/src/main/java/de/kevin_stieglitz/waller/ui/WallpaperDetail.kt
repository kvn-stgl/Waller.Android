package de.kevin_stieglitz.waller.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.facebook.common.executors.UiThreadImmediateExecutorService
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.DataSource
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import de.kevin_stieglitz.waller.R
import kotlinx.android.synthetic.main.wallpaper_detail_fragment.*
import timber.log.Timber


class WallpaperDetail : Fragment() {

    private val wallpaperArgs by navArgs<WallpaperDetailArgs>()

    private lateinit var viewModel: WallpaperDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        sharedElementEnterTransition = transition
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wallpaper_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            NavHostFragment.findNavController(this).navigateUp()
        }

        viewModel = ViewModelProviders.of(this).get(WallpaperDetailViewModel::class.java)

        viewModel.wallpaper(wallpaperArgs.imageId).observe(viewLifecycleOwner, Observer {
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
