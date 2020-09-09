package com.sample.gallery.presentation.actions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IncludeViralViewModel : ViewModel() {
    var isViralSelected: Boolean = true

    private val includeViralLiveData = MutableLiveData<Boolean>()

    fun getIncludeViralLiveData(): LiveData<Boolean> = includeViralLiveData

    fun setIncludeViralLiveData(include: Boolean?) {
        includeViralLiveData.value = include
    }
}