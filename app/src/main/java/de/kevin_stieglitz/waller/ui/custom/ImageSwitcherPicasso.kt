package de.kevin_stieglitz.waller.ui.custom

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageSwitcher

import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class ImageSwitcherPicasso(val context: Context, val imageSwitcher: ImageSwitcher, val loading: View? = null) : Target {
    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        loading?.visibility = View.VISIBLE;
    }

    override fun onBitmapFailed(errorDrawable: Drawable?) {
        loading?.visibility = View.GONE;
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        imageSwitcher.setImageDrawable(BitmapDrawable(context.resources, bitmap))
        loading?.visibility = View.GONE;
    }
}