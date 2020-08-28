package com.sample.gallery.domain.model

data class GalleryResponse (

	val galleryList : List<Gallery>,
	val success : Boolean,
	val status : Int
)
