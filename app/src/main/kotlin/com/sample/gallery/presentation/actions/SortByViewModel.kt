package com.sample.gallery.presentation.actions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SortByViewModel : ViewModel() {
    var selectedSortByPosition: Int = 0

    private val sortPositionLiveData = MutableLiveData<Int>()

    fun getSortPositionLiveData() = sortPositionLiveData

    fun setSortPositionLiveData(position: Int) {
        sortPositionLiveData.value = position
    }
}