package jafari.alireza.contacts.feature.dialog


import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.DialogFragment
import jafari.alireza.contacts.R


open class BaseDialogFragment : DialogFragment() {
    var mOnPositiveClickListener: OnClickListener? = null
    var mOnNegativeClickListener: OnClickListener? = null
    var mOnDismissListener: DialogInterface.OnDismissListener? = null
    var dismissOnPositiveClick: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_Dialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        // request a window without the title
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
//        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        return dialog
    }

    interface OnClickListener {
        fun onClick(tag: String?)
    }


}
