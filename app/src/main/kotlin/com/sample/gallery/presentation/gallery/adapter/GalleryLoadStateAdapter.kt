package com.sample.gallery.presentation.gallery.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class GalleryLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<GalleryLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: GalleryLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): GalleryLoadStateViewHolder {
        return GalleryLoadStateViewHolder.create(parent, retry)
    }
}