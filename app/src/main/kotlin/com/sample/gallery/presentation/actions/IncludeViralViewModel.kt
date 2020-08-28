package com.sample.gallery.presentation.actions

import android.os.Bundle
import androidx.fragment.app.FragmentManager
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

    fun showIncludeViralDialog(fragmentManager: FragmentManager) {
        val includeViralDialog = IncludeViralDialog(this@IncludeViralViewModel)
        includeViralDialog.arguments = Bundle().apply {
            putBoolean("checkedItem", isViralSelected)
        }
        includeViralDialog.show(
            fragmentManager,
            IncludeViralDialog::class.simpleName
        )
    }
}