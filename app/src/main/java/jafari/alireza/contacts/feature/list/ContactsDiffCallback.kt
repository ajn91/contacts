package jafari.alireza.contacts.feature.list

import androidx.recyclerview.widget.DiffUtil
import jafari.alireza.contacts.model.domain.list.ListModel

class ContactsDiffCallback(
    private val mOldList: List<ListModel>,
    private val mNewList: List<ListModel>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return mOldList.size
        }

        override fun getNewListSize(): Int {
            return mNewList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return mOldList[oldItemPosition].contactId == mNewList[newItemPosition].contactId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = mOldList[oldItemPosition]
            val newItem = mNewList[newItemPosition]

            return (newItem == oldItem)
        }

    }