package jafari.alireza.contacts.model.repository.list

import androidx.recyclerview.widget.DiffUtil
import jafari.alireza.contacts.model.domain.list.ListModel
import jafari.alireza.contacts.model.source.local.list.entity.ContactEntity

class ContactsLocalDiffCallback(
    private val mOldList: List<ContactEntity>,
    private val mNewList: List<ContactEntity>
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

            return (newItem.equals(oldItem))
        }

    }