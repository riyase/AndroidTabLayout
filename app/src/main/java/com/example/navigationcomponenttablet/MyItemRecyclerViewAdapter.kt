package com.example.navigationcomponenttablet

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponenttablet.databinding.FragmentItemBinding
import com.example.navigationcomponenttablet.dummy.DummyContent
import com.example.navigationcomponenttablet.viewmodel.ItemViewModel

class MyItemRecyclerViewAdapter(
    val isLargeScreen: Boolean,
    val items: ArrayList<DummyContent>,
    val viewModel: ItemViewModel,
    val triggerSelection: (Boolean ,Int, DummyContent) -> Unit
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.MyItemViewHolder>(){

    private var selectedItem = viewModel.selectedItemPos

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyItemRecyclerViewAdapter.MyItemViewHolder {
        val binding = DataBindingUtil.inflate<FragmentItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.fragment_item,
            parent,
            false
        )

        return MyItemViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: MyItemViewHolder, position: Int) {
        holder.binding.item = items[position]
        if (isLargeScreen) {
            if (position == selectedItem) {
                holder.setColor()
            } else {
                holder.clearColor()
            }
        }
    }

    override fun getItemCount() = items.size

    inner class MyItemViewHolder(
        itemView: View,
        val binding: FragmentItemBinding
    ): RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if (isLargeScreen) {
                viewModel.itemSelected(bindingAdapterPosition, items[bindingAdapterPosition])
                notifyItemChanged(selectedItem)
                selectedItem = bindingAdapterPosition
                notifyItemChanged(bindingAdapterPosition)
            } else {
                triggerSelection(false, bindingAdapterPosition, items[bindingAdapterPosition])
            }
        }

        fun setColor() {
            itemView.setBackgroundColor(Color.parseColor("#4003DAC5"))
        }

        fun clearColor() {
            itemView.setBackgroundColor(Color.parseColor("#00000000"))
        }

        override fun onLongClick(p0: View?): Boolean {
            triggerSelection(true, bindingAdapterPosition, items[bindingAdapterPosition])
            return true
        }
    }
}