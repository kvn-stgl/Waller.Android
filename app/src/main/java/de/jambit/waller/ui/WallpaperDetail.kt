package de.jambit.waller.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.squareup.picasso.Picasso
import de.jambit.waller.R
import kotlinx.android.synthetic.main.view_item_wallpaper.*


class WallpaperDetail : Fragment() {

    private val wallpaperArgs by navArgs<WallpaperDetailArgs>()

    private lateinit var viewModel: WallpaperDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        postponeEnterTransition()

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

        image_wallpaper.transitionName = getString(R.string.transition_wallpaper, wallpaperArgs.id)

        viewModel = ViewModelProviders.of(this).get(WallpaperDetailViewModel::class.java)

        viewModel.wallpaper(wallpaperArgs.id).observe(viewLifecycleOwner, Observer {
            Picasso.with(context)
                .load(it.imageUrl)
                .fit()
                .noFade()
                .centerCrop()
                .into(
                    image_wallpaper
//                    ,object : Callback {
//                        override fun onSuccess() {
//                            startPostponedEnterTransition()
//                        }
//
//                        override fun onError() {
//                            // empty method. (looks ugly)
//                            startPostponedEnterTransition()
//                        }
//                    }
                )
        })
    }

}
