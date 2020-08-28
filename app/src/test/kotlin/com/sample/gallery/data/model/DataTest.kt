package com.sample.gallery.data.model

import DataFixtures
import org.junit.Assert.assertEquals
import org.junit.Test

class DataTest {

    @Test
    fun `Data data to domain`() {
        val data = DataFixtures.getGalleryResponseData().data
        val gallery = DataFixtures.getGalleryResponse().galleryList

        assertEquals(gallery, data.toDomain())
    }
}