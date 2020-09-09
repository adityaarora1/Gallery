package com.sample.gallery.presentation.actions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilterSectionViewModel : ViewModel() {
    var selectedSectionPosition: Int = 0

    private val filterPositionLiveData = MutableLiveData<Int>()

    fun getFilterPositionLiveData(): LiveData<Int> = filterPositionLiveData

    fun setFilterPositionLiveData(position: Int) {
        filterPositionLiveData.value = position
    }
}