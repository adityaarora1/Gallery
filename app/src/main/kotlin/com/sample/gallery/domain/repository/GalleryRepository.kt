package com.sample.gallery.domain.repository

import com.sample.gallery.domain.model.GalleryResponse


interface GalleryRepository {

    suspend fun getGallery(
        section: String,
        sort: String,
        window: String,
        page: Int,
        showViral: Boolean
    ): GalleryResponse
}