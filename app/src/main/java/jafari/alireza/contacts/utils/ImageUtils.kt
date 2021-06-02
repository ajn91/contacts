package jafari.alireza.contacts.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import jafari.alireza.contacts.R


object ImageUtils {

    fun showImage(
        url: String?,
        imageView: ImageView,
        @DrawableRes placeHolder: Int = R.color.colorAccent
    ) {
            imageView.load(url) {
                crossfade(true)
                placeholder(placeHolder)
//                transformations(CircleCropTransformation())

//            Glide.with(context)
//                .load(url)
//                .transform(CenterInside(), RoundedCorners(16))
//                .placeholder(placeHolder)
//                .error(placeHolder)
//                .dontAnimate()
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(imageView)
        }
    }


}
