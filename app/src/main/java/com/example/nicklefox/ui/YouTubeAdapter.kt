package com.example.nicklefox.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nicklefox.R
import com.example.nicklefox.data_holder.DataItem

class YoutubeAdapter(private val contract: ClickContract) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(ListItemCallback()) {

    class ListItemCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DataItem,
            newItem: DataItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.youtube_item, parent, false)
        return YoutubeCatalogHolder(view, this)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listOfData = getItem(position)
        if (holder is YoutubeCatalogHolder) {
            holder.bind(listOfData)
        }
    }

    interface ClickContract {
        fun itemClick(
            adapterPosition: Int,
            videoID: String?,
        )
    }

    fun performClick(
        adapterPosition: Int,
        videoID: String?,
    ) = contract.itemClick(adapterPosition, videoID)

}