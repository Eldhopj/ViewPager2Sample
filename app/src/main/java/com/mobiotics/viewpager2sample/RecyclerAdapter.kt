package com.mobiotics.viewpager2sample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobiotics.viewpager2sample.databinding.ItemViewpagerBinding

class RecyclerAdapter(private val context: Context) :
    ListAdapter<ModelClass, RecyclerView.ViewHolder>(DiffCallback()) {
    private var listener: ((ModelClass, Int) -> Unit)? = null

    /**
     * lambda will forward our click from adapter to our activity/fragment
     * NOTE : we can pass lambda via constructor of the adapter also
     */
    fun setOnContentClickListener(listener: (ModelClass, Int) -> Unit) {
        this.listener = listener
    }

    class DiffCallback : DiffUtil.ItemCallback<ModelClass>() {
        override fun areItemsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder { // this method calls when ever our view method is created , ie; the instance of ViewHolder class is created
        val inflater = LayoutInflater.from(context)
        val binding = ItemViewpagerBinding.inflate(
            inflater,
            parent, false
        )
        return ItemViewHolder(binding, listener)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) { //populate the data into the list_item (View Holder), as we scroll
        (holder as? ItemViewHolder)?.bindData(getItem(position))
    }

    inner class ItemViewHolder(
        binding: ItemViewpagerBinding, // with the help of "itemView" we ge the views from xml
        private val listener: ((ModelClass, Int) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private val textView = binding.textView
        private var item: ModelClass? = null

        init {
            itemView.setOnClickListener(this)
        }

        //Binding of data happens in here
        fun bindData(item: ModelClass) {
            this.item = item
            val position = adapterPosition
            if (position == RecyclerView.NO_POSITION) {
                return
            }
            with(item) {
                textView.text = id.toString()
            }
        }

        override fun onClick(v: View) {
            val position = adapterPosition // Get the index of the view holder
            if (position == RecyclerView.NO_POSITION && item == null) { // Makes sure this position is still valid
                return
            }
            /**
             * All the clicks will come in here*/
            if (v === itemView) {
                listener?.invoke(
                    item!!,
                    position
                ) // we catch the click on the item view then pass it over the interface and then to our activity
            }
        }
    }

    //-------------------------------------Manipulating RecyclerView--------------------------------//

    /**
     * Submitting a list into a particular position (if position is not valid, we will append at the end) or appending at the end
     * */
    fun submitListItem(newDataList: MutableList<ModelClass>?, position: Int = itemCount) {
        if (!newDataList.isNullOrEmpty()) {
            val newItems = ArrayList<ModelClass>()
            newItems.addAll(currentList)
            if (itemCount >= position) {
                newItems.addAll(position, newDataList)
            } else {
                newItems.addAll(newDataList)
            }
            submitList(newItems)
        }
    }
}
