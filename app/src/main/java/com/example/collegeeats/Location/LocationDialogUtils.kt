package com.example.collegeeats.Location

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Window
import com.example.collegeeats.R

object LocationDialogUtils {
    fun showLocationDialog(dialog: Dialog) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_location_not_enabled)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(true)
        dialog.show()
    }

    fun showLocationPermissionDeniedDialog(
        context: Context,
        onPositiveButtonClick: DialogInterface.OnClickListener
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Permission Denied")
        builder.setMessage("Location permission is required to access your location. Please enable the permission to proceed.")
        builder.setPositiveButton("Grant Permission", onPositiveButtonClick)
        builder.setNegativeButton("Cancel", null)
        builder.setCancelable(false)
        builder.create().show()
    }
}
