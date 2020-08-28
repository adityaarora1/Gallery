package com.sample.gallery.presentation.actions

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class IncludeViralDialog(private val viewModel: IncludeViralViewModel) : DialogFragment() {
    private var checkedItems: Boolean = true

    private var positiveListener: DialogInterface.OnClickListener =
        DialogInterface.OnClickListener { dialog, which ->
            this.viewModel.setIncludeViralLiveData(checkedItems)
        }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bundle: Bundle? = arguments
        checkedItems = bundle?.getBoolean("checkedItem") ?: true
        val b: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        b.setTitle("Include Viral")
        b.setMultiChoiceItems(
            Actions.viral,
            booleanArrayOf(checkedItems)
        ) { _, _, isChecked ->
            checkedItems = isChecked
        }
        b.setPositiveButton("OK", positiveListener)
        b.setNegativeButton("Cancel", null)
        return b.create()
    }
}