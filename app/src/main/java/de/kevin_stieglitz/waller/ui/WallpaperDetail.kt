package de.kevin_stieglitz.waller.ui

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso
import de.kevin_stieglitz.waller.ImageSwitcherPicasso
import de.kevin_stieglitz.waller.R
import de.kevin_stieglitz.waller.extension.cacheFile
import kotlinx.android.synthetic.main.wallpaper_detail_fragment.*


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

        imageswitcher_wallpaper.transitionName = getString(R.string.transition_wallpaper, wallpaperArgs.imageId)

        // Back Button
        back.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }

        // Animation when switching to another image.
        val animOut = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
        val animIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)

        imageswitcher_wallpaper.inAnimation = animIn
        imageswitcher_wallpaper.outAnimation = animOut
        imageswitcher_wallpaper.setFactory {
            val imageView = PhotoView(context)
            imageView.layoutParams =
                FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            return@setFactory imageView
        }

        imageswitcher_wallpaper.setImageDrawable(
            BitmapDrawable(
                requireContext().resources,
                cacheFile(requireContext(), wallpaperArgs.imageThumb)
            )
        )

        viewModel = ViewModelProviders.of(this).get(WallpaperDetailViewModel::class.java)

        val imageSwitcher = ImageSwitcherPicasso(requireContext(), imageswitcher_wallpaper, progress)

        viewModel.wallpaper(wallpaperArgs.imageId).observe(viewLifecycleOwner, Observer {
            Picasso.with(context)
                .load(it.path)
                .noPlaceholder()
                .into(imageSwitcher)
        })
    }
}
