package com.example.collegeeats.CommonUtils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.example.collegeeats.R

class DialogManager(private val context: Context) {
        private val dialog: Dialog = Dialog(context)

        init {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_process)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setCancelable(false)
        }

        fun show() {
            dialog.show()
        }

        fun dismiss() {
            dialog.dismiss()
        }
    }