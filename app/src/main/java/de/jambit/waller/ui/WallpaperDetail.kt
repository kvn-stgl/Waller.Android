package de.jambit.waller.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageSwitcher
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.squareup.picasso.Picasso
import de.jambit.waller.ImageSwitcherPicasso
import de.jambit.waller.R
import de.jambit.waller.extension.cachedPicasso
import kotlinx.android.synthetic.main.view_item_wallpaper.*


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

        image_wallpaper.transitionName = getString(R.string.transition_wallpaper, preview.id)
        image_wallpaper.cachedPicasso(requireContext(), preview.thumb)

        viewModel = ViewModelProviders.of(this).get(WallpaperDetailViewModel::class.java)

        val imageSwitcher = ImageSwitcherPicasso(requireContext(), ImageSwitcher(context))
        viewModel.wallpaper(preview.id).observe(viewLifecycleOwner, Observer {
            Picasso.with(context)
                .load(it.imageUrl)
                .noPlaceholder()
                .into(imageSwitcher)
        })
    }

}
