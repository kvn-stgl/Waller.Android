package de.kevin_stieglitz.waller.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import de.kevin_stieglitz.waller.R
import de.kevin_stieglitz.waller.adapter.WallpaperAdapter
import de.kevin_stieglitz.waller.extension.NetworkState
import de.kevin_stieglitz.waller.extension.Status
import de.kevin_stieglitz.waller.model.WallpaperSearchOptions
import kotlinx.android.synthetic.main.wallpaper_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class WallpaperListFragment : Fragment() {

    private val wallpaperListArgs by navArgs<WallpaperListFragmentArgs>()

    private val model: WallpaperListViewModel by viewModel()

    private val searchOptions: WallpaperSearchOptions by lazy { WallpaperSearchOptions(wallpaperListArgs.displayType) }

    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wallpaper_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val navController = NavHostFragment.findNavController(this)
        bottom_nav.setupWithNavController(navController)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model.search(searchOptions)

        initAdapter()
        initSwipeToRefresh()
        initNetworkState()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_wallpaper, menu)

        val searchItem = menu.findItem(R.id.search)
        initSearch(searchItem)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
        return navigated || super.onOptionsItemSelected(item)
    }

    private fun initSearch(searchItem: MenuItem) {
        searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchOptions.searchText = query
                model.search(searchOptions)

                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?) = true
            
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                searchOptions.searchText = null
                model.search(searchOptions)
                return true
            }
        })
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
