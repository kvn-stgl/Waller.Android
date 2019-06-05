package de.kevin_stieglitz.waller.ui

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import de.kevin_stieglitz.waller.extension.NetworkState
import io.reactivex.Flowable

/**
 * Data class that is necessary for a UI to show a listing and interact w/ the rest of the system
 */
data class WallpaperListViewState<T>(

    // the LiveData of paged lists for the UI to observe
    val pagedList: Flowable<PagedList<T>>,

    // represents the network request status to show to the user
    val networkState: LiveData<NetworkState>,

    // represents the refresh status to show to the user. Separate from networkState, this
    // value is importantly only when refresh is requested.
    val refreshState: LiveData<NetworkState>,

    // refreshes the whole data and fetches it from scratch.
    val refresh: () -> Unit,

    // retries any failed requests.
    val retry: () -> Unit
)