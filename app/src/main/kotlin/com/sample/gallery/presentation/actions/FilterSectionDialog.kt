package com.sample.gallery.presentation.actions

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class FilterSectionDialog(private val viewModel: FilterSectionViewModel) : DialogFragment() {
    private var positiveListener: DialogInterface.OnClickListener =
        DialogInterface.OnClickListener { dialog, which ->
            val alert: AlertDialog = dialog as AlertDialog
            val position: Int = alert.listView.checkedItemPosition
            this.viewModel.setFilterPositionLiveData(position)
        }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bundle: Bundle? = arguments
        val position: Int = bundle?.getInt("position")!!
        val b: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        b.setTitle("Filter section")
        b.setSingleChoiceItems(Actions.sections, position, null)
        b.setPositiveButton("OK", positiveListener)
        b.setNegativeButton("Cancel", null)
        return b.create()
    }
}