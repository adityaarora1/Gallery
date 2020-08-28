package com.sample.gallery.data.repository

import com.sample.base.SafeApiRequest
import com.sample.gallery.data.model.toDomain
import com.sample.gallery.data.remote.RetrofitService
import com.sample.gallery.domain.model.GalleryResponse
import com.sample.gallery.domain.repository.GalleryRepository
import javax.inject.Inject

internal class GalleryRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService
) : GalleryRepository, SafeApiRequest() {

    override suspend fun getGallery(
        section: String,
        sort: String,
        window: String,
        page: Int,
        showViral: Boolean
    ): GalleryResponse =
        apiRequest { retrofitService.getGallery(section, sort, window, page, showViral) }.toDomain()
}