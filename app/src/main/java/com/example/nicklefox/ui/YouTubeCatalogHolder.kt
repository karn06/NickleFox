package com.example.nicklefox.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.nicklefox.R
import com.example.nicklefox.data_holder.DataItem

class YoutubeCatalogHolder(view: View,private val youtubeAdapter: YoutubeAdapter) :
    RecyclerView.ViewHolder(view), View.OnClickListener {

    private val title = itemView.findViewById<TextView>(R.id.title)
    private val description = itemView.findViewById<TextView>(R.id.description)
    private val image = itemView.findViewById<ImageView>(R.id.image)

    private var videoID: String? = null

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(listOfData: DataItem) {

        val snippet = listOfData.snippet
        videoID = listOfData.id.videoId
        title.text = snippet.title
        description.text = snippet.description

        Glide.with(itemView.context)
            .load(snippet.thumbnails.default.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(image)
    }

    override fun onClick(p0: View?) {
        youtubeAdapter.performClick(adapterPosition,videoID)
    }

}