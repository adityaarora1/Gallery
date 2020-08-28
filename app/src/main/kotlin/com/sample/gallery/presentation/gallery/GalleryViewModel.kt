package com.sample.gallery.presentation.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sample.gallery.domain.model.Gallery
import com.sample.gallery.domain.usecase.GalleryDataSource
import com.sample.gallery.domain.usecase.GetGalleryUseCase
import kotlinx.coroutines.flow.Flow

internal class GalleryViewModel @ViewModelInject constructor(
    private val getGalleryUseCase: GetGalleryUseCase
) : ViewModel() {

    private var section: String = "hot"
    private var sortBy: String = "viral"
    private var window: String = "day"
    private var showViral: Boolean = true
    private val pageSize = 20
    var lastViewType: GalleryFragment.ViewType = GalleryFragment.ViewType.GRID

    private val selectedGalleryItem = MutableLiveData<Gallery>()
    private val galleryPagingData = MutableLiveData<Flow<PagingData<Gallery>>>()

    fun getSelectedGalleryItem(): LiveData<Gallery> = selectedGalleryItem

    fun selectGalleryItem(item: Gallery) {
        selectedGalleryItem.value = item
    }

    fun getGalleryPagingData(): LiveData<Flow<PagingData<Gallery>>> = galleryPagingData

    init {
        nowPlaying()
    }

    fun nowPlaying(
        section: String = this.section,
        sort: String = this.sortBy,
        window: String = this.window,
        showViral: Boolean = this.showViral
    ) {
        this.section = section
        this.sortBy = sort
        this.window = window
        this.showViral = showViral

        galleryPagingData.value = Pager(PagingConfig(pageSize = pageSize)) {
            GalleryDataSource(
                useCase = getGalleryUseCase,
                section = section,
                sort = sort,
                window = window,
                showViral = showViral
            )
        }.flow.cachedIn(viewModelScope)
    }
}