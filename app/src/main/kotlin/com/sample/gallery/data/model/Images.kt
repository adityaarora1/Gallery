package com.sample.gallery.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Images (
	val id : String?,
	val title : String?,
	val description : String?,
	val datetime : Long?,
	val type : String?,
	val animated : Boolean ?,
	val width : Int ?,
	val height : Int ?,
	val size : Int ?,
	val views : Int ?,
	val bandwidth : Long ?,
	val vote : String ?,
	val favorite : Boolean ?,
	val nsfw : String ?,
	val section : String ?,
	val account_url : String ?,
	val account_id : String ?,
	val is_ad : Boolean ?,
	val in_most_viral : Boolean ?,
	val has_sound : Boolean ?,
	val tags : List<String> ?,
	val ad_type : Int ?,
	val ad_url : String ?,
	val edited : Int ?,
	val in_gallery : Boolean ?,
	val link : String ?,
	val mp4_size : Int ?,
	val mp4 : String ?,
	val gifv : String ?,
	val hls : String ?,
	val processing : Processing ?,
	val comment_count : String ?,
	val favorite_count : String ?,
	val ups : String ?,
	val downs : String ?,
	val points : String ?,
	val score : String?
)