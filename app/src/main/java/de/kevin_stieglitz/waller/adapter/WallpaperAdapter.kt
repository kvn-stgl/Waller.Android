package de.kevin_stieglitz.waller.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.kevin_stieglitz.waller.R
import de.kevin_stieglitz.waller.model.WallpaperSearchEntry
import de.kevin_stieglitz.waller.ui.WallpaperListFragmentDirections


class WallpaperAdapter(val activity: Activity) : PagedListAdapter<WallpaperSearchEntry, WallpaperAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        } else {
            // Null defines a placeholder item - PagedListAdapter will automatically invalidate
            // this row when the actual object is loaded from the database
            holder.clear()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_item_wallpaper, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var title: TextView = view.findViewById(R.id.title_wallpaper)
        private var image: ImageView = view.findViewById(R.id.image_wallpaper)
        private var view: CardView = view as CardView

        fun bind(value: WallpaperSearchEntry) {
            image.transitionName = activity.getString(R.string.transition_wallpaper, value.id)

            title.text = value.resolution

            Picasso.with(image.context)
                .load(value.thumbs?.large)
                .into(image)

            view.setOnClickListener {

                val detailAction = WallpaperListFragmentDirections.actionNavigationDetail(value.id, value.thumbs?.large)

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    androidx.core.util.Pair.create(image, image.transitionName)
                )

                val extras = ActivityNavigatorExtras(options)

                // We only use this for fragment transactions
//                val extras = FragmentNavigatorExtras(
//                    image to image.transitionName
//                )
                Navigation.findNavController(it).navigate(detailAction, extras)
            }
        }

        fun clear() {
            title.text = null
            image.setImageDrawable(null)
            view.setOnClickListener(null)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WallpaperSearchEntry>() {
            override fun areContentsTheSame(oldItem: WallpaperSearchEntry, newItem: WallpaperSearchEntry): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: WallpaperSearchEntry, newItem: WallpaperSearchEntry): Boolean =
                oldItem.id == newItem.id
        }
    }
}