package com.sample.gallery.presentation.actions

import android.os.Bundle
import androidx.fragment.app.FragmentManager
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

    fun showFilterSectionDialog(fragmentManager: FragmentManager) {
        val filterSectionDialog = FilterSectionDialog(this@FilterSectionViewModel)
        filterSectionDialog.arguments = Bundle().apply {
            putInt("position", selectedSectionPosition)
            filterSectionDialog.show(
                fragmentManager,
                FilterSectionDialog::class.simpleName
            )
        }
    }
}