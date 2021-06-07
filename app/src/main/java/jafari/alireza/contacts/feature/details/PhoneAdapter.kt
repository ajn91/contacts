package jafari.alireza.contacts.feature.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.scopes.ActivityScoped
import jafari.alireza.contacts.databinding.PhoneItemBinding
import jafari.alireza.contacts.feature.appinterface.OnItemClickListener
import javax.inject.Inject

@ActivityScoped
class PhoneAdapter @Inject constructor() :
    RecyclerView.Adapter<PhoneAdapter.ItemViewHolder>() {
    var onItemClickListener: OnItemClickListener<String>? = null

    private var mItems: MutableList<String> = mutableListOf()


    fun setItems(items: List<String>) {
        val diffCallback = DiffCallback(mItems, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback, true)
        mItems.clear()
        mItems.addAll(items)
        diffResult.dispatchUpdatesTo(this)

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(
            PhoneItemBinding.inflate(inflater, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = mItems[position]
        holder.binding.phone = item
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(item)
        }

    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    class ItemViewHolder(var binding: PhoneItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    inner class DiffCallback(
        private val mOldList: List<String>,
        private val mNewList: List<String>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return mOldList.size
        }

        override fun getNewListSize(): Int {
            return mNewList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return mOldList[oldItemPosition] == mNewList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return mOldList[oldItemPosition] == mNewList[newItemPosition]

        }

    }

}
