package de.jambit.waller

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageSwitcher
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target


class ImageSwitcherPicasso(val context: Context, val imageSwitcher: ImageSwitcher) : Target {
    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

    }

    override fun onBitmapFailed(errorDrawable: Drawable?) {

    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        imageSwitcher.setImageDrawable(BitmapDrawable(context.resources, bitmap))
    }
}