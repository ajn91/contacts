package jafari.alireza.contacts.utils

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import jafari.alireza.contacts.feature.dialog.BaseDialogFragment
import jafari.alireza.contacts.feature.dialog.GeneralDialogFragment
import jafari.alireza.contacts.feature.dialog.LoadingDialogFragment


object MessageUtils {


    fun showDialog(
        fragmentManager: FragmentManager?,
        dialogFragment: DialogFragment,
        isCancelable: Boolean = false,
        tag: String? = dialogFragment.tag
    ) {

        dialogFragment.isCancelable = isCancelable

        if (fragmentManager != null) {
            dialogFragment.show(fragmentManager, tag)
        }
    }

    fun showLoadingDialog(
        fragmentManager: FragmentManager?,
        tag: String?,
        isCancelable: Boolean = false,
    ) {
        val loadingDialog = LoadingDialogFragment.newInstance()
        showDialog(fragmentManager, loadingDialog, isCancelable, tag)
    }

    fun showInfoDialog(
        fragmentManager: FragmentManager?,
        title: String?,
        message: String,
        tag: String? = "loading",
        isCancelable: Boolean = false,
    ) {
        val infoDialog = GeneralDialogFragment.newInstance(title, message)
        showDialog(fragmentManager, infoDialog, isCancelable, tag)
    }

    fun showDialogWithButton(
        fragmentManager: FragmentManager,
        title: String?,
        message: String,
        tag: String,
        onPositiveClickListener: BaseDialogFragment.OnClickListener? = null,
        onNegativeClickListener: BaseDialogFragment.OnClickListener? = null,
        onDismissListener: DialogInterface.OnDismissListener? = null,
        dismissOnPositiveClick: Boolean = true,
        isCancelable: Boolean = true
    ): GeneralDialogFragment {
        val generalDialogFragment = GeneralDialogFragment.newInstance(title, message)
        generalDialogFragment.mOnPositiveClickListener = onPositiveClickListener
        generalDialogFragment.mOnNegativeClickListener = onNegativeClickListener
        generalDialogFragment.mOnDismissListener = onDismissListener
        generalDialogFragment.dismissOnPositiveClick = dismissOnPositiveClick
        generalDialogFragment.isCancelable = isCancelable
        generalDialogFragment.show(fragmentManager, tag)
        return generalDialogFragment
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }



}