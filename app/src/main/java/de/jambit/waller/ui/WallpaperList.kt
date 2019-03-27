package de.jambit.waller.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import de.jambit.waller.R
import de.jambit.waller.adapter.WallpaperAdapter
import kotlinx.android.synthetic.main.wallpaper_list_fragment.*


class WallpaperList : Fragment() {

    private val wallpaperListArgs by navArgs<WallpaperListArgs>()

    private lateinit var viewModel: WallpaperListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wallpaper_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(WallpaperListViewModel::class.java)

        val adapter = WallpaperAdapter(requireContext())

        viewModel.wallpapers(wallpaperListArgs.displayType).observe(viewLifecycleOwner, Observer {
            if (it?.images != null) {
                adapter.list = it.images
            }
        })

        recyclerview_images.adapter = adapter
        recyclerview_images.layoutManager = GridLayoutManager(context, 2)
    }

}
