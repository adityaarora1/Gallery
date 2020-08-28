package com.sample.gallery.presentation.actions

import android.os.Bundle
import androidx.fragment.app.FragmentManager
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

    fun showChangeWindowDialog(fragmentManager: FragmentManager) {
        val changeWindowDialog = ChangeWindowDialog(this@ChangeWindowViewModel)
        changeWindowDialog.arguments = Bundle().apply {
            putInt("position", selectedWindowPosition)
        }
        changeWindowDialog.show(
            fragmentManager,
            ChangeWindowDialog::class.simpleName
        )
    }
}