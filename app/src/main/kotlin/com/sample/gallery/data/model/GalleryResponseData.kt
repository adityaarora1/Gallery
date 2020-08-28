package com.sample.gallery.data.model

import com.sample.gallery.domain.model.GalleryResponse
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GalleryResponseData(
    val data: List<Data>,
    val success: Boolean,
    val status: Int
)

internal fun GalleryResponseData.toDomain(): GalleryResponse = GalleryResponse(
	galleryList = data.filter { it.id != null }.toDomain(),
	success = success,
	status = status
)