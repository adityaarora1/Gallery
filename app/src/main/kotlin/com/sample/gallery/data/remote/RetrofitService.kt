package com.sample.gallery.data.remote


import com.sample.gallery.data.model.GalleryResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("gallery/{section}/{sort}/{window}/{page}")
    suspend fun getGallery(
        @Path("section") section: String,
        @Path("sort") sort: String,
        @Path("window") window: String,
        @Path("page") page: Int,
        @Query("showViral") showViral: Boolean
    ): Response<GalleryResponseData>
}