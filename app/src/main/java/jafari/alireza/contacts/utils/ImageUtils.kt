package jafari.alireza.contacts.utils

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import jafari.alireza.contacts.R


object ImageUtils {

    fun showImage(
        uri: Uri?,
        imageView: ImageView,
        @DrawableRes placeHolder: Int = R.drawable.ic_avatar
    ) {
        imageView.load(uri) {
            crossfade(true)
            fallback(placeHolder)
            error(placeHolder)

        }
    }


}
