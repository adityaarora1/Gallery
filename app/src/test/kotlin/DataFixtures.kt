import com.sample.gallery.data.model.Data
import com.sample.gallery.data.model.GalleryResponseData
import com.sample.gallery.domain.model.Gallery
import com.sample.gallery.domain.model.GalleryResponse

object DataFixtures {

    internal fun getGalleryResponseData(): GalleryResponseData = GalleryResponseData(
        data = listOf(getData()),
        success = true,
        status = 200
    )

    internal fun getEmptyGalleryResponseData(): GalleryResponseData = GalleryResponseData(
        data = listOf(),
        success = true,
        status = 200
    )

    internal fun getGalleryResponse(): GalleryResponse = GalleryResponse(
        galleryList = listOf(getGallery()),
        success = true,
        status = 200
    )

    internal fun getData(): Data = Data(
        id = "XiRpm9L",
        title = "Make It A Double",
        description = null,
        datetime = 1598570664,
        cover = "2I5spWm",
        cover_width = 854,
        cover_height = 854,
        account_url = "Onthemargin",
        account_id = 134415127,
        privacy = "hidden",
        layout = "blog",
        views = 35199,
        link = "https://imgur.com/a/XiRpm9L",
        ups = 896,
        downs = 26,
        points = 870,
        score = 887,
        is_album = true,
        vote = null,
        favorite = false,
        nsfw = false,
        section = "",
        comment_count = 25,
        favorite_count = 274,
        topic = "No Topic",
        topic_id = 29,
        images_count = 1,
        in_gallery = true,
        is_ad = false,
        tags = null,
        ad_type = 0,
        ad_url = "",
        in_most_viral = true,
        include_album_ads = false,
        images = null
    )

    internal fun getGallery(): Gallery = Gallery(
        id = "XiRpm9L",
        title = "Make It A Double",
        description = "",
        datetime = 1598570664,
        coverUrl = "https://i.imgur.com/2I5spWm.jpg",
        coverWidth = 854,
        coverHeight = 854,
        accountUrl = "Onthemargin",
        accountId = 134415127,
        privacy = "hidden",
        layout = "blog",
        views = 35199,
        link = "https://imgur.com/a/XiRpm9L",
        ups = 896,
        downs = 26,
        points = 870,
        score = 887,
        isAlbum = true,
        vote = "",
        favorite = false,
        nsfw = false,
        section = "",
        commentCount = 25,
        favoriteCount = 274,
        topic = "No Topic",
        topicId = 29,
        imagesCount = 1,
        inGallery = true,
        isAd = false,
        adType = 0,
        adUrl = "",
        inMostViral = true,
        includeAlbumAds = false
    )
}