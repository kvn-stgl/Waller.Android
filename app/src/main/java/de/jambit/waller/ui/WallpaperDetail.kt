package de.jambit.waller.ui

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
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.squareup.picasso.Picasso
import de.jambit.waller.ImageSwitcherPicasso
import de.jambit.waller.R
import de.jambit.waller.extension.cacheFile
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

        val preview = wallpaperArgs.wallpaperPreview

        imageswitcher_wallpaper.transitionName = getString(de.jambit.waller.R.string.transition_wallpaper, preview.id)
        // image_wallpaper.cachedPicasso(requireContext(), preview.thumb)

        // Animation when switching to another image.
        val animOut = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
        val animIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)

        imageswitcher_wallpaper.inAnimation = animIn
        imageswitcher_wallpaper.outAnimation = animOut
        imageswitcher_wallpaper.setFactory {
            val imageView = ImageView(context)
            imageView.layoutParams =
                FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            return@setFactory imageView
        }
        imageswitcher_wallpaper.setImageDrawable(
            BitmapDrawable(
                requireContext().resources,
                cacheFile(requireContext(), preview.thumb)
            )
        )

        viewModel = ViewModelProviders.of(this).get(WallpaperDetailViewModel::class.java)

        val imageSwitcher = ImageSwitcherPicasso(requireContext(), imageswitcher_wallpaper)
        viewModel.wallpaper(preview.id).observe(viewLifecycleOwner, Observer {
            Picasso.with(context)
                .load(it.imageUrl)
                .noPlaceholder()
                .into(imageSwitcher)
        })
    }

}
