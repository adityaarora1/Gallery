package com.sample.gallery.domain.usecase

import com.sample.gallery.domain.model.GalleryResponse
import com.sample.gallery.domain.repository.GalleryRepository
import javax.inject.Inject

class GetGalleryUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository
) {

    suspend fun getGallery(
        section: String,
        sort: String,
        window: String,
        page: Int,
        showViral: Boolean
    ): GalleryResponse = galleryRepository.getGallery(section, sort, window, page, showViral)
}
