package com.sample.gallery.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Processing (
	val status : String?
)