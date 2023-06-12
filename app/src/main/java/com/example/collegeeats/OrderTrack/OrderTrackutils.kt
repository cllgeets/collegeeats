package com.example.collegeeats.OrderTrack

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.collegeeats.R
import com.example.collegeeats.databinding.FragmentOrderTrackingBinding
import com.google.android.gms.wallet.PaymentDataRequest
import com.google.android.gms.wallet.TransactionInfo
import com.google.android.gms.wallet.WalletConstants
import com.google.android.material.bottomsheet.BottomSheetBehavior

class OrderTrackutils(val context: Context, val binding: FragmentOrderTrackingBinding) {

    fun initialisation_views(){
        val bottomSheet = binding.eventBottomSheet
        var behavior = BottomSheetBehavior.from(bottomSheet)
        behavior.peekHeight = 0
        behavior.isDraggable = true
        behavior.state = BottomSheetBehavior.STATE_HIDDEN

        binding.closeButton.setOnClickListener{
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            behavior.peekHeight = 0
        }
    }

    fun payment_adapter(){
        val spinner = binding.paymentMethodSpinner
        val genderList = context.resources.getStringArray(R.array.payment_methods)
        val paymentadapter = ArrayAdapter(context, R.layout.spinner_item, genderList)
        spinner.adapter = paymentadapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedPaymentMethod = genderList[position]
                // update your UI with the selected payment method
                var currentSelectedId = selectedPaymentMethod
                Log.d("234", position.toString())

                if (currentSelectedId == "Phonepe"){
                    values.payment_method = "Phonepe"
                }else{
                    values.payment_method = "Gpay"
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }
        }

    }
}

object values{
    var payment_method: String = "Gpay";
}