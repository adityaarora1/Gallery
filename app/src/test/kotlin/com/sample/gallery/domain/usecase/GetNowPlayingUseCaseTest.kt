package com.sample.gallery.domain.usecase

import DataFixtures
import com.sample.gallery.data.model.toDomain
import com.sample.gallery.domain.repository.GalleryRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetGalleryUseCaseTest {

    @MockK
    internal lateinit var galleryRepository: GalleryRepository
    private lateinit var getGalleryUseCase: GetGalleryUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getGalleryUseCase = GetGalleryUseCase(galleryRepository)
    }

    @Test
    fun `return gallery data`() = runBlocking {
        val galleryResponse = DataFixtures.getGalleryResponseData().toDomain()

        coEvery {
            galleryRepository.getGallery(
                section = "hot",
                sort = "viral",
                window = "day",
                page = 1,
                showViral = true
            )
        } returns galleryResponse

        val result = getGalleryUseCase.getGallery(
            section = "hot",
            sort = "viral",
            window = "day",
            page = 1,
            showViral = true
        )

        assertEquals(result, galleryResponse)
    }
}