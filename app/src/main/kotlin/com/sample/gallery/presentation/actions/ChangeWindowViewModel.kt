package com.sample.gallery.presentation.actions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChangeWindowViewModel : ViewModel() {
    var selectedWindowPosition: Int = 0

    private val windowPositionLiveData = MutableLiveData<Int>()

    fun getWindowPositionLiveData(): LiveData<Int> = windowPositionLiveData

    fun setWindowPositionLiveData(position: Int) {
        windowPositionLiveData.value = position
    }
}