package jafari.alireza.contacts.feature.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jafari.alireza.contacts.R


class LoadingDialogFragment : BaseDialogFragment() {

    companion object {
        const val TAG = "LoadingDialog"
        fun newInstance()= LoadingDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.loading_dialog_fragment, container, false)
    }


}
