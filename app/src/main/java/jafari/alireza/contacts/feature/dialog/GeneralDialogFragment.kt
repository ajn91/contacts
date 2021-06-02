package jafari.alireza.contacts.feature.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import jafari.alireza.contacts.R
import jafari.alireza.contacts.databinding.GeneralDialogFragmentBinding


class GeneralDialogFragment : BaseDialogFragment() {

    companion object {
        const val TITLE_ARG = "titleArg"
        const val MESSAGE_ARG = "messageArg"
        fun newInstance(title: String?, message: String): GeneralDialogFragment {
            val fragment = GeneralDialogFragment()
            val args = Bundle()
            args.putString(TITLE_ARG, title)
            args.putString(MESSAGE_ARG, message)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<GeneralDialogFragmentBinding>(
            inflater, R.layout.general_dialog_fragment, container, false
        )
        val view: View = binding.root
        val args = arguments

        if (args != null) {
            val message = args.getString(MESSAGE_ARG)
            val title: String? = args.getString(TITLE_ARG)
            if (title.isNullOrBlank())
                binding.txtDialogTitle.visibility = View.GONE
            else
                binding.txtDialogTitle.text = title
            if (message.isNullOrBlank())
                binding.txtDialogMessage.visibility = View.GONE
            else
                binding.txtDialogMessage.text =
                    HtmlCompat.fromHtml(message, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }

        if (mOnNegativeClickListener == null)
            binding.btnDialogCancel.visibility = View.GONE
        else
            binding.btnDialogCancel.setOnClickListener {
                    mOnNegativeClickListener?.onClick(tag)

                dismiss()
            }

        binding.btnDialogOk.setOnClickListener {
                mOnPositiveClickListener?.onClick(tag)
            if (dismissOnPositiveClick)
                dismiss()
        }
        return view
    }


}
