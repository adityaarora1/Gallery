package com.sample.gallery.presentation.actions

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SortByViewModel : ViewModel() {
    var selectedSortByPosition: Int = 0

    private val sortPositionLiveData = MutableLiveData<Int>()

    fun getSortPositionLiveData() = sortPositionLiveData

    fun setSortPositionLiveData(position: Int) {
        sortPositionLiveData.value = position
    }

    fun showSortByDialog(fragmentManager: FragmentManager) {
        val sortByDialog = SortByDialog(this@SortByViewModel)
        sortByDialog.arguments = Bundle().apply {
            putInt("position", selectedSortByPosition)
        }
        sortByDialog.show(
            fragmentManager,
            SortByDialog::class.simpleName
        )
    }
}