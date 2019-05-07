package com.gustavo.pagedlistappandroid.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gustavo.pagedlistappandroid.R
import com.gustavo.pagedlistappandroid.api.response.Result
import com.gustavo.pagedlistappandroid.view.custom.RoundImageView
import com.squareup.picasso.Picasso

class ComicAdapter internal constructor(private val context: Context) :
    PagedListAdapter<Result, ComicAdapter.ComicViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldResult: Result, newResult: Result): Boolean {
                return oldResult.id == newResult.id
            }

            override fun areContentsTheSame(oldResult: Result, newResult: Result): Boolean {
                return oldResult == newResult
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicAdapter.ComicViewHolder = ComicViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.item_comic,
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: ComicAdapter.ComicViewHolder, position: Int) {
        val result = getItem(position)

        if (result != null) {

            holder.nameComic.text = result.volume.name

            Picasso
                .get()
                .load(result.image.small_url)
                .into(holder.imageView)

            holder.yearComic.text = result.date_last_updated.substring(0, 4)
        } else {
            Toast.makeText(context, "Result is null", Toast.LENGTH_LONG).show()
        }
    }

    inner class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameComic: TextView
        var yearComic: TextView
        var imageView: RoundImageView

        init {
            nameComic = itemView.findViewById(R.id.text_comic_name)
            yearComic = itemView.findViewById(R.id.text_comic_year)
            imageView = itemView.findViewById(R.id.img_comic_folder)
        }
    }
}
