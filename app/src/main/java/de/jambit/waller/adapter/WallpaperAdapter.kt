package de.jambit.waller.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.jambit.waller.R
import de.jambit.waller.model.WallpaperPreview
import de.jambit.waller.ui.WallpaperListDirections


class WallpaperAdapter(val context: Context) : RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {
    var list: List<WallpaperPreview> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = list[position]
        holder.bind(number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_item_wallpaper, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var title: TextView = view.findViewById(R.id.title_wallpaper)
        private var image: ImageView = view.findViewById(R.id.image_wallpaper)
        private var view: CardView = view as CardView

        fun bind(value: WallpaperPreview) {
            image.transitionName = context.getString(R.string.transition_wallpaper, value.id)

            title.text = value.resolution
            Picasso.with(image.context)
                .load(value.thumb)
                .into(image)

            view.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    image to image.transitionName
                )

                val detailAction = WallpaperListDirections.actionNavigationDetail(value)
                Navigation.findNavController(it).navigate(detailAction, extras)
            }
        }
    }
}