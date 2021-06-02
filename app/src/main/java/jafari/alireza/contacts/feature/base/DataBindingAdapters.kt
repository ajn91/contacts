package jafari.alireza.contacts.feature.base

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import jafari.alireza.contacts.utils.ImageUtils


@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, imageUrl: String?) {
    ImageUtils.showImage( imageUrl, view)

}

//@BindingAdapter("imageResource")
//fun setImageUri(view: ImageView, imageUri: String?) {
//    if (imageUri == null) {
//        view.setImageURI(null)
//    } else {
//        view.setImageURI(Uri.parse(imageUri))
//    }
//}
//
//    @BindingAdapter("imageResource")
//    fun setImageUri(view: ImageView, imageUri: Uri?) {
//        view.setImageURI(imageUri)
//    }
//
@BindingAdapter("imageResource")
fun setImageDrawable(view: ImageView, drawable: Drawable?) {
    view.setImageDrawable(drawable)
}
//
//    @BindingAdapter("imageResource")
//    fun setImageResource(imageView: ImageView, resource: Int) {
//        imageView.setImageResource(resource)
//    }

//    @BindingAdapter("setBold")
//    fun setBold(view: TextView, isBold: Boolean) {
//        if (isBold) {
//            view.setTypeface(null, Typeface.BOLD)
//        } else {
//            view.setTypeface(null, Typeface.NORMAL)
//        }
//    }
