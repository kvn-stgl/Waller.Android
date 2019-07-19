package de.kevin_stieglitz.waller.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import de.kevin_stieglitz.waller.R
import de.kevin_stieglitz.waller.adapter.WallpaperAdapter
import de.kevin_stieglitz.waller.extension.NetworkState
import de.kevin_stieglitz.waller.extension.Status
import kotlinx.android.synthetic.main.wallpaper_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class WallpaperListFragment : Fragment() {

    private val wallpaperListArgs by navArgs<WallpaperListFragmentArgs>()

    private val model: WallpaperListViewModel by viewModel()

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

        model.search(wallpaperListArgs.displayType)

        initAdapter()
        initSwipeToRefresh()
        initNetworkState()
    }

    private fun initAdapter() {
        val adapter = WallpaperAdapter(requireActivity())
        Timber.d("WallpaperListFragment.initAdapter")

        model.posts.observe(this, Observer {
            Timber.d("WallpaperListFragment.initAdapter::observe WallpaperSearchEntry")
            adapter.submitList(it)
        })

        recyclerview_images.adapter = adapter
        recyclerview_images.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun initSwipeToRefresh() {
        model.refreshState.observe(this, Observer {
            swipe_refresh.isRefreshing = it == NetworkState.LOADING
        })
        swipe_refresh.setOnRefreshListener {
            model.refresh()
        }
    }

    private fun initNetworkState() {
        model.networkState.observe(this, Observer {
            if (it.status == Status.FAILED) {
                Toast.makeText(context, it.msg, Toast.LENGTH_LONG).show()
            }
        })
    }

}
