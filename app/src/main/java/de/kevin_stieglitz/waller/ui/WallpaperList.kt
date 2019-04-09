package de.kevin_stieglitz.waller.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import de.kevin_stieglitz.waller.R
import de.kevin_stieglitz.waller.adapter.WallpaperAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = NavHostFragment.findNavController(this)
        bottom_nav.setupWithNavController(navController)
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
