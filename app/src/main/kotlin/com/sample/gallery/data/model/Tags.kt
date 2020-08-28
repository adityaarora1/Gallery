package com.sample.gallery.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tags (
	val name : String ?,
	val display_name : String ?,
	val followers : Int ?,
	val total_items : Int ?,
	val following : Boolean ?,
	val is_whitelisted : Boolean ?,
	val background_hash : String ?,
	val thumbnail_hash : String ?,
	val accent : String ?,
	val background_is_animated : Boolean ?,
	val thumbnail_is_animated : Boolean ?,
	val is_promoted : Boolean ?,
	val description : String ?,
	val logo_hash : String ?,
	val logo_destination_url : String?
)