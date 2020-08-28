package com.sample.gallery.presentation.gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sample.gallery.R
import com.sample.gallery.domain.model.Gallery
import kotlinx.android.synthetic.main.item_list_gallery.view.*

internal class GalleryAdapter(
    private val itemClick: (Gallery) -> Unit
) : PagingDataAdapter<Gallery, GalleryAdapter.ViewHolder>(DataDiff) {

    enum class ViewType {
        DATA,
        FOOTER
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gallery: Gallery? = getItem(position)
        holder.itemView.textTitle.text = gallery?.title
        if (gallery?.description.isNullOrEmpty()) {
            holder.itemView.textDescription.isVisible = false
        } else {
            holder.itemView.textDescription.isVisible = true
            holder.itemView.textDescription.text = gallery?.description
        }

        Glide.with(holder.itemView.context)
            .load(gallery?.coverUrl)
            .centerCrop()
            .thumbnail(0.5f)
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.itemView.imgGallery)

        gallery?.let { it ->
            holder.itemView.setOnClickListener { _ ->
                itemClick.invoke(it)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            ViewType.DATA.ordinal
        } else {
            ViewType.FOOTER.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_list_gallery, parent, false)
        )
    }

    object DataDiff : DiffUtil.ItemCallback<Gallery>() {

        override fun areItemsTheSame(oldItem: Gallery, newItem: Gallery): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Gallery, newItem: Gallery): Boolean {
            return oldItem == newItem
        }
    }
}
