package com.example.dogtask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogtask.R
import com.example.dogtask.utilities.Utils
import kotlinx.android.synthetic.main.dog_list_item.view.*


class BreedsAdapter(
    private var context: Context,
    private var breeds: MutableList<String> = mutableListOf(),
    private var isSearch: Boolean = false,
    private val onPostClick: ((position: Int) -> Unit)
) : RecyclerView.Adapter<BreedsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutId = if (isSearch) R.layout.search_dog_list_item else R.layout.dog_list_item
        val view: View = LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false)
        return ViewHolder(view, onPostClick)
    }

    override fun getItemCount(): Int {
        return breeds.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(breeds[position])
    }

    // -- Update the main data source at once
    fun updateDogList(newList: MutableList<String>) {
        breeds = newList
        notifyDataSetChanged()
    }

    // -- Return the main data source of recycler view
    fun getBreeds(): MutableList<String> {
        return breeds
    }

    // -- Update main list item by item
    fun updateItem(breed: String) {
        breeds.add(breed)
        notifyItemInserted(breeds.lastIndex)
    }

    inner class ViewHolder internal constructor(
        @NonNull view: View,
        onPostClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val dogImg = view.ivDog!!

        init {
            view.setOnClickListener { onPostClick.invoke(bindingAdapterPosition) }
        }

        // -- binding image to the View
        fun bind(breed: String) {
            val realName = if (!isSearch) "${breed}_${Utils.generateSubCatDog()}" else breed
            Glide.with(context).load(Utils.getImageFromDrawable(context, realName)).into(dogImg)
        }
    }
}