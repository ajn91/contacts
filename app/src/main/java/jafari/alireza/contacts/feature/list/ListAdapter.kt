package jafari.alireza.contacts.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.scopes.ActivityScoped
import jafari.alireza.contacts.databinding.ListItemBinding
import jafari.alireza.contacts.feature.appinterface.OnItemClickListener
import jafari.alireza.contacts.model.domain.list.ListModel
import javax.inject.Inject

@ActivityScoped
class ListAdapter @Inject constructor() :
    RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {
    var onItemClickListener: OnItemClickListener<ListModel>? = null

    private var mItems : MutableList<ListModel> = mutableListOf()


    fun setItems(items: List<ListModel>) {
        val diffCallback = ContactsDiffCallback(mItems, items)
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
            ListItemBinding.inflate(inflater, parent, false)
        )
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = mItems[position]
        holder.binding.item = item
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(item)
        }

    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    class ItemViewHolder(var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)




}
