package com.sample.gallery.data.model

import com.sample.gallery.data.remote.ImageApi
import com.sample.gallery.domain.model.Gallery
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    val id: String?,
    val title: String?,
    val description: String?,
    val datetime: Long?,
    val cover: String?,
    val cover_width: Int?,
    val cover_height: Int?,
    val account_url: String?,
    val account_id: Int?,
    val privacy: String?,
    val layout: String?,
    val views: Int?,
    val link: String?,
    val ups: Int?,
    val downs: Int?,
    val points: Long?,
    val score: Long?,
    val is_album: Boolean?,
    val vote: String?,
    val favorite: Boolean?,
    val nsfw: Boolean?,
    val section: String?,
    val comment_count: Int?,
    val favorite_count: Int?,
    val topic: String?,
    val topic_id: Int?,
    val images_count: Int?,
    val in_gallery: Boolean?,
    val is_ad: Boolean?,
    val tags: List<Tags>?,
    val ad_type: Int?,
    val ad_url: String?,
    val in_most_viral: Boolean?,
    val include_album_ads: Boolean?,
    val images: List<Images>?
)

internal fun List<Data>.toDomain(): List<Gallery> = map {
    Gallery(
        id = it.id!!,
        title = it.title ?: "",
        description = it.description ?: "",
        datetime = it.datetime ?: 0,
        coverUrl = it.cover?.let { coverString ->
            ImageApi.coverImageUrlFromId(coverString)
        } ?: "",
        coverWidth = it.cover_width ?: 0,
        coverHeight = it.cover_height ?: 0,
        accountUrl = it.account_url ?: "",
        accountId = it.account_id ?: 0,
        privacy = it.privacy ?: "",
        layout = it.layout ?: "",
        views = it.views ?: 0,
        link = it.link ?: "",
        ups = it.ups ?: 0,
        downs = it.downs ?: 0,
        points = it.points ?: 0,
        score = it.score ?: 0,
        isAlbum = it.is_album ?: false,
        vote = it.vote ?: "",
        favorite = it.favorite ?: false,
        nsfw = it.nsfw ?: false,
        section = it.section ?: "",
        commentCount = it.comment_count ?: 0,
        favoriteCount = it.favorite_count ?: 0,
        topic = it.topic ?: "",
        topicId = it.topic_id ?: 0,
        imagesCount = it.images_count ?: 0,
        inGallery = it.in_gallery ?: false,
        isAd = it.is_ad ?: false,
        adType = it.ad_type ?: 0,
        adUrl = it.ad_url ?: "",
        inMostViral = it.in_most_viral ?: false,
        includeAlbumAds = it.include_album_ads ?: false
    )
}